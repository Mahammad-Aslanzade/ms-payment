package com.example.mspayment.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExist extends RuntimeException {
    private String message;
    private String error;

    public AlreadyExist(String message, String error) {
        super(error);
        this.message = message;
        this.error = error;
    }

}
