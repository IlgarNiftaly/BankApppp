package com.binary.uniTech.validation;

import com.binary.uniTech.validation.constraint.InfoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InfoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoValid {
    String message() default "Info structure is incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
