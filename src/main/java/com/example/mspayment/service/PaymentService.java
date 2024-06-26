package com.example.mspayment.service;


import com.example.mspayment.dao.entity.PaymentEntity;
import com.example.mspayment.dao.repository.PaymentRepository;
import com.example.mspayment.mapper.PaymentMapper;
import com.example.mspayment.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public List<PaymentDto> getAllPayments(){
        log.info("ACTION.getAllPayments.start");
        List<PaymentEntity> paymentEntites = paymentRepository.findAll();
        List<PaymentDto> paymentDtos = paymentMapper.mapToDtos(paymentEntites);
        log.info("ACTION.getAllPayments.end");
        return paymentDtos;
    }

    public void savePayment(PaymentDto paymentDto) {
        log.info("ACTION.savePayment.start");
        PaymentEntity paymentEntity = paymentMapper.mapToEntity(paymentDto);
        paymentRepository.save(paymentEntity);
        log.info("ACTION.savePayment.end");
    }

    // test ele yoxlanmayib
    public void editPayment(String paymentId ,PaymentDto paymentDto){
        log.info("ACTION.editPayment.start paymentId : {} | req : {}", paymentId , paymentDto );
        PaymentEntity paymentEntity = paymentMapper.mapToEntity(paymentDto);
        paymentRepository.save(paymentEntity);
        log.info("ACTION.editPayment.end paymentId : {} | req : {}", paymentId , paymentDto );
    }

}
