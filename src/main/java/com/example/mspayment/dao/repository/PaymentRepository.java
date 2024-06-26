package com.example.mspayment.dao.repository;

import com.example.mspayment.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity , String> {
}
