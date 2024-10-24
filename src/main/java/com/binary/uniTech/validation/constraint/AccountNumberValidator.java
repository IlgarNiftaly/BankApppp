package com.binary.uniTech.validation.constraint;

import com.binary.uniTech.validation.AccountNumberValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountNumberValidator implements ConstraintValidator<AccountNumberValid, String> {

    @Override
    public void initialize(AccountNumberValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String accountNumber, ConstraintValidatorContext constraintValidatorContext) {
        return accountNumber != null &&
                accountNumber.length() == 16 &&
                accountNumber.matches("^[0-9]{9,18}$");
    }
}
