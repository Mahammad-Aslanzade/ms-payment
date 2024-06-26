package com.example.mspayment.dao.repository;

import com.example.mspayment.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<AccountEntity , String> {
    AccountEntity findByAccountNumber(String accountNumber);
}
