package com.example.mspayment.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReqDto {
    @NotNull(message = "From Card can't be null")
    private String fromCard;
    @NotNull(message = "To Card can't be null")
    private String toCard;
    @NotNull(message = "To Card can't be null")
    private double amount;

}