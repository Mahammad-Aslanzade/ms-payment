package com.example.mspayment.dao.entity;

import com.example.mspayment.enums.Currency;
import com.example.mspayment.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fromCard;
    private String toCard;
    private double amount;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
