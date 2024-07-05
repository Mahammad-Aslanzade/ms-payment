package com.example.mspayment.controller;

import com.example.mspayment.exceptions.AlreadyExist;
import com.example.mspayment.exceptions.BalanceIsNotValid;
import com.example.mspayment.exceptions.NotFound;
import com.example.mspayment.model.exceptions.AlreadyExistDto;
import com.example.mspayment.model.exceptions.BalanceIsNotValidDto;
import com.example.mspayment.model.exceptions.NotFoundDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public String handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Unsupported media type: ").append(ex.getContentType());
//        builder.append(". Supported media types are ");
//        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
//        return builder.toString();
//    }

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundDto handleNotFound(NotFound e){
        log.error(e.getError());
        return new NotFoundDto(e.getMessage() , 404);
    }

    @ExceptionHandler(BalanceIsNotValid.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public BalanceIsNotValidDto handleBalanceErr(BalanceIsNotValid e){
        log.error(e.getError());
        return  new BalanceIsNotValidDto(e.getMessage(), e.getBalance() , e.getAmount());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println(
                ex.getBindingResult().getFieldErrors() + ex.getMessage()
        );
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExist.class)
    public AlreadyExistDto handleAlreadyExist(AlreadyExist e){
        log.error(e.getError());
        return new AlreadyExistDto(e.getMessage());
    }
}
