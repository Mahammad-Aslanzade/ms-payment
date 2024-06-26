package com.example.mspayment.controller;

import com.example.mspayment.model.PayReqDto;
import com.example.mspayment.model.account.AccountReqDto;
import com.example.mspayment.model.account.AccountResDto;
import com.example.mspayment.model.PaymentDto;
import com.example.mspayment.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<AccountResDto> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping
    public AccountReqDto createAccount(@Valid @RequestBody AccountReqDto accountReqDto){
        return accountService.createAccount(accountReqDto);
    }

    @PostMapping("/payment")
    public PaymentDto paymentProcess(@Valid @RequestBody PaymentDto paymentDto) {
        accountService.paymentProcess(paymentDto);
        return paymentDto;
    }

    // new operation
    @PostMapping("/pay")
    public PayReqDto paymentOperation(@Valid @RequestBody PayReqDto payDto) {
        return accountService.paymentOperation(payDto);

    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable String accountId){
        accountService.deleteAccount(accountId);
    }



}
