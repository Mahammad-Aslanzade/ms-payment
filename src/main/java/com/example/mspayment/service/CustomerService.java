package com.example.mspayment.service;

import com.example.mspayment.dao.entity.CustomerEntity;
import com.example.mspayment.dao.repository.CustomerRepository;
import com.example.mspayment.exceptions.NotFound;
import com.example.mspayment.mapper.CustomerMapper;
import com.example.mspayment.model.customer.CustomerReqDto;
import com.example.mspayment.model.customer.CustomerResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final ImageService imageService;

    public List<CustomerResDto> getAllCustomers() {
        log.info("Action.getAllCustomers.start");
        var customerEntities = customerRepository.findAll();
        var customerDtos = customerMapper.mapToDto(customerEntities);
        log.info("Action.getAllCustomers.end");
        return customerDtos;
    }

    public CustomerResDto getCustomer(String customerId) {
        log.info("Action.getCustomer.start customerId -> {}", customerId);
        var customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("CUSTOMER_NOT_FOUND"));
        var customerDto = customerMapper.mapToDto(customerEntity);
        log.info("Action.getCustomer.end customerId -> {}", customerId);
        return customerDto;
    }

    public void saveCustomer(CustomerReqDto customerReqDto, MultipartFile image) throws IOException {
        CustomerEntity customerEntity = customerMapper.mapToEntity(customerReqDto);
        String imageUrl = imageService.upLoadImageAndGetPath(image, "customer");
        customerEntity.setImage(imageUrl);
        customerRepository.save(customerEntity);
    }

    public void deleteCustomer(String customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("CUSTOMER_NOT_FOUND"));

        imageService.deleteImage(customerEntity.getImage());
        customerRepository.deleteById(customerId);
    }

    public void editCustomer(String customerId, CustomerReqDto customerReqDto) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFound(
                        "CUSTOMER_NOT_FOUND"
                        , "Action.ERROR.findCustomerById customerId : " + customerId));
        CustomerEntity updatedCustomer = customerMapper.mapToEntity(customerReqDto);
        updatedCustomer.setId(customerEntity.getId());
        customerRepository.save(updatedCustomer);
    }


}
