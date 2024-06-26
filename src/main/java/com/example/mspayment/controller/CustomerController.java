package com.example.mspayment.controller;

import com.example.mspayment.model.customer.CustomerReqDto;
import com.example.mspayment.model.customer.CustomerResDto;
import com.example.mspayment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public CustomerResDto getCustomer(@PathVariable String customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public void saveCustomer(@RequestPart(name = "file") CustomerReqDto customerReqDto,
                             @RequestPart(name = "image") MultipartFile image) throws IOException {
        customerService.saveCustomer(customerReqDto, image);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("/{customerId}")
    public void editCustomer(@PathVariable String customerId, @ModelAttribute CustomerReqDto customerReqDto) throws IOException {
        customerService.editCustomer(customerId, customerReqDto);
    }


}
