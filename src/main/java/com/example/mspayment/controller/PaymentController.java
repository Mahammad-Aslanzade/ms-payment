package com.example.mspayment.controller;

import com.example.mspayment.model.PaymentDto;
import com.example.mspayment.service.ImageService;
import com.example.mspayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final ImageService imageService;

    @GetMapping
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping
    public void savePayment(@RequestBody PaymentDto paymentDto){
        paymentService.savePayment(paymentDto);
    }

    @PutMapping("/{paymentId}")
    public void editPayment(@PathVariable String paymentId ,@RequestBody PaymentDto paymentDto){
        paymentService.editPayment(paymentId ,paymentDto);
    }
}
