package com.binary.uniTech.validation.constraint;

import com.binary.uniTech.validation.FinValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FinValidator implements ConstraintValidator<FinValid, String> {

    @Override
    public void initialize(FinValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String fin, ConstraintValidatorContext constraintValidatorContext) {
        return fin != null &&
                fin.length()==7;
    }
}
