package com.example.mspayment.model.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BalanceIsNotValidDto {
    private String message;
    private Double currentBalance;
    private Double amount;
}
