package com.binary.uniTech.validation;


import com.binary.uniTech.validation.constraint.AccountNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AccountNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountNumberValid {
    String message() default "Account number structure is incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
