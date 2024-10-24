package com.binary.uniTech.validation;


import com.binary.uniTech.validation.constraint.FinValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FinValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FinValid {
    String message() default "UserPin structure is incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
