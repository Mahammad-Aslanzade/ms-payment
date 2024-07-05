package com.example.mspayment.validations.validator;

import com.example.mspayment.validations.validation.MyEmailConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MyEmailValidator implements ConstraintValidator<MyEmailConstraint,String> {

    @Override
    public void initialize(MyEmailConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext context) {
        return emailField.length() == 3;
    }
}
