package com.binary.uniTech.validation.constraint;

import com.binary.uniTech.validation.InfoValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InfoValidator implements ConstraintValidator<InfoValid, String> {
    @Override
    public void initialize(InfoValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String info, ConstraintValidatorContext constraintValidatorContext) {
        return info != null &&
                info.length()>3 &&
                info.length()<30;
    }

}
