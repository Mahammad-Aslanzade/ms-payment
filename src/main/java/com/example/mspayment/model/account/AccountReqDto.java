package com.example.mspayment.model.account;

import com.example.mspayment.enums.Currency;
import com.example.mspayment.validations.validation.MyEmailConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountReqDto {
    @NotNull(message = "currency must be valid")
    private Currency currency;
    private double balance;
    @NotNull(message = "account must belong to customer")
    private String customerId;
}
