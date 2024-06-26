package com.example.mspayment.model.customer;


import com.example.mspayment.model.account.AccountResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResDto {
    private String id;
    private String name;
    private String surname;
    private String image;
    private List<AccountResDto> account;
}
