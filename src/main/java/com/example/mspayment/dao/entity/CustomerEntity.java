package com.example.mspayment.dao.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String surname;
    private String image;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<AccountEntity> account;
}
