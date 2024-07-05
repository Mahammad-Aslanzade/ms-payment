package com.example.mspayment.dao.entity;

import com.example.mspayment.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private Date date;
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
