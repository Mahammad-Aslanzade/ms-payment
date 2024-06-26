package com.example.mspayment.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceIsNotValid extends RuntimeException {
    private String error;
    private String message;

    private Double balance;
    private Double amount;

    public BalanceIsNotValid(String error, String message , Double balance , Double amount) {
        super(error);
        this.error = error;
        this.message = message;
        this.balance = balance;
        this.amount = amount;
    }

}
