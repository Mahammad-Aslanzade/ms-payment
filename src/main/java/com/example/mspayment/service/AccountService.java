package com.example.mspayment.service;

import com.example.mspayment.dao.entity.AccountEntity;
import com.example.mspayment.dao.entity.CustomerEntity;
import com.example.mspayment.dao.repository.AccountRepository;
import com.example.mspayment.dao.repository.CustomerRepository;
import com.example.mspayment.enums.Currency;
import com.example.mspayment.enums.Status;
import com.example.mspayment.exceptions.AlreadyExist;
import com.example.mspayment.exceptions.BalanceIsNotValid;
import com.example.mspayment.exceptions.NotFound;
import com.example.mspayment.mapper.AccountMapper;
import com.example.mspayment.model.PaymentReqDto;
import com.example.mspayment.model.account.AccountReqDto;
import com.example.mspayment.model.account.AccountResDto;
import com.example.mspayment.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    private final PaymentService paymentService;

    private final CustomerRepository customerRepository;

    public AccountReqDto createAccount(AccountReqDto accountReqDto) {
        log.info("Action.createAccount.start");
        CustomerEntity customer = customerRepository.findById(accountReqDto.getCustomerId())
                .orElseThrow(() -> new NotFound(
                        "CUSTOMER_NOT_FOUND",
                        "Action.ERROR.createAccount customerId : " + accountReqDto.getCustomerId() + " not found "
                ));
        AccountEntity account = accountMapper.mapToEntity(accountReqDto);
        String accountNumber = customer.getId() + "-" + account.getCurrency();

        AccountEntity checkAccount = accountRepository.findByAccountNumber(accountNumber);

        if (checkAccount != null) {
            throw new AlreadyExist(
                    "Customer has already had account in " + accountReqDto.getCurrency() + " !",
                    "Action.ERROR.createAccount requestDto : " + accountReqDto
                    );
        }

        account.setAccountNumber(accountNumber);
        account.setCustomer(customer);
        accountRepository.save(account);
        log.info("Action.createAccount.end");
        return accountReqDto;
    }

    public List<AccountResDto> getAllAccounts() {
        log.info("Action.getAllAccounts.start");
        List<AccountResDto> accountResDtos = accountMapper.listToDtos(accountRepository.findAll());
        log.info("Action.getAllAccounts.end");
        return accountResDtos;
    }

    public void deleteAccount(String accountId) {
        log.info("Action.deleteAccount.start accountId : {}", accountId);

        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFound(
                        "ACCOUNT_NOT_FOUND",
                        "Action.ERROR.deleteAccount accountId : " + accountId
                ));
        accountRepository.delete(account);

        log.info("Action.deleteAccount.end accountId : {}", accountId);
    }

    // simple payment @Deprecated
    public PaymentDto paymentProcess(PaymentDto paymentDto) {
        var accFrom = accountRepository.findById(paymentDto.getFromCard())
                .orElseThrow(() -> new RuntimeException("ACCOUNT_FROM_NOT_FOUND"));
        var accTo = accountRepository.findById(paymentDto.getToCard())
                .orElseThrow(() -> new RuntimeException("ACCOUNT_TO_NOT_FOUND"));
        ;
        System.out.println(accFrom);
        System.out.println(accTo);
        var balanceFrom = accFrom.getBalance();
        var balanceTo = accTo.getBalance();

        accFrom.setBalance(balanceFrom - paymentDto.getAmount());
        accTo.setBalance(balanceTo + paymentDto.getAmount());

        accountRepository.save(accFrom);
        accountRepository.save(accTo);

        return paymentDto;
    }

    // new one
    private AccountEntity findAccountByAccountNumber(String accountNumber) {
        AccountEntity account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFound(
                    "ACCOUNT_NOT_FOUND",
                    "Action.ERROR.findAccountByAccountNumber accountNumber : " + accountNumber
            );
        }
        return account;
    }

    private double calculateCommission(AccountEntity accFrom, AccountEntity accTo, Double amount) {
        double commisionForAcc = 0.001; // 0.1%
        double commisionForCur = 0.01; // 1%

        double totalCommission = 0;

        if (accFrom.getCustomer() != accTo.getCustomer()) {
            totalCommission += amount * commisionForAcc;
        }

        if (accFrom.getCurrency() != accTo.getCurrency()) {
            totalCommission += amount * commisionForCur;
        }
        return totalCommission;
    }

    public PaymentReqDto paymentOperation(PaymentReqDto payDto) {
        AccountEntity accountFrom = findAccountByAccountNumber(payDto.getFromCard());
        AccountEntity accountTo = findAccountByAccountNumber(payDto.getToCard());
        // Calculations of commision and convertion
        double amount = payDto.getAmount();
        double totalCommision = calculateCommission(accountFrom, accountTo, amount);
        double totalAmountWithCommission = amount + totalCommision;
        double convertedMoney = Currency.convertMoney(accountFrom.getCurrency(), accountTo.getCurrency(), amount);
        payDto.setAmount(convertedMoney);
        //


        if (totalAmountWithCommission > accountFrom.getBalance()) {
            paymentService.savePayment(payDto, Status.FAILED);
            throw new BalanceIsNotValid(
                    "Action.Error.BalanceIsNotEnough account : " + accountFrom.getAccountNumber(),
                    "BALANCE_IS_NOT_ENOUGH",
                    accountFrom.getBalance(),
                    totalAmountWithCommission
            );
        }

        accountFrom.setBalance(accountFrom.getBalance() - totalAmountWithCommission);
        accountTo.setBalance(accountTo.getBalance() + convertedMoney);

        paymentService.savePayment(payDto, Status.COMPLETED);
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
        return payDto;
    }
}
