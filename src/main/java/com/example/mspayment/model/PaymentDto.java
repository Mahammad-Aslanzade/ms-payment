package com.example.mspayment.model;

import com.example.mspayment.enums.Currency;
import com.example.mspayment.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String id;
    @NotNull(message = "From Card can't be null")
    private String fromCard;
    @NotNull(message = "To Card can't be null")
    private String toCard;
    private double amount;
    private Currency currency;
    private Status status;
}
