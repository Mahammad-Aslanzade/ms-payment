package com.example.mspayment.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReqDto {
    private String id;
    private String name;
    private String surname;

}
