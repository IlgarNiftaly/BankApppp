package com.binary.uniTech.validation;

import com.binary.uniTech.validation.constraint.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValid {
    String message() default "Email structure is incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
