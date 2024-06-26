package com.example.mspayment.mapper;

import com.example.mspayment.dao.entity.CustomerEntity;
import com.example.mspayment.model.customer.CustomerReqDto;
import com.example.mspayment.model.customer.CustomerResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResDto mapToDto(CustomerEntity customerEntity);
    List<CustomerResDto> mapToDto(List<CustomerEntity> customerEntity);

    @Mapping(target = "image" , ignore = true)
    CustomerEntity mapToEntity(CustomerReqDto customerReqDto);
}
