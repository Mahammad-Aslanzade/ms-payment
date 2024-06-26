package com.example.mspayment.mapper;

import com.example.mspayment.dao.entity.AccountEntity;
import com.example.mspayment.model.account.AccountReqDto;
import com.example.mspayment.model.account.AccountResDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    List<AccountResDto> listToDtos(List<AccountEntity> accountEntities);

    AccountEntity mapToEntity(AccountReqDto accountReqDto);
    AccountEntity mapToEntity(AccountResDto accountResDto);

    AccountResDto mapToResDto(AccountEntity accountEntity);
}
