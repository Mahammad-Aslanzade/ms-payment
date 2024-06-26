package com.example.mspayment.dao.repository;

import com.example.mspayment.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity , String> {
}
