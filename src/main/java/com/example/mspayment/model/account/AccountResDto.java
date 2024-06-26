package com.example.mspayment.model.account;

import com.example.mspayment.enums.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResDto {
    private String id;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double balance;
}
