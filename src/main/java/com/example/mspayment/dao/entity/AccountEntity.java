package com.example.mspayment.dao.entity;


import com.example.mspayment.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "customer_id" )
    private CustomerEntity customer;

    public AccountEntity(String id, String accountNumber, Currency currency, double balance, CustomerEntity customer) {
        this.id = id;
        this.accountNumber = accountNumber + "-" + customer.getId();
        this.currency = currency;
        this.balance = balance;
        this.customer = customer;
    }
}




