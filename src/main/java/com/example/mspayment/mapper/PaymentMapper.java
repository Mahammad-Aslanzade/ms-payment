package com.example.mspayment.mapper;

import com.example.mspayment.dao.entity.PaymentEntity;
import com.example.mspayment.model.PaymentDto;
import com.example.mspayment.model.PaymentReqDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    List<PaymentDto> mapToDtos (List<PaymentEntity> paymentEntities);

    PaymentEntity mapToEntity (PaymentDto paymentDto);

    PaymentDto mapToPaymentDto(PaymentReqDto paymentReqDto);
}
