package com.smartjob.api.users.adapters.in.rest.dtos.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Invalid password. 8 Character length. Contain at least one digit (0-9). Contain at least one lowercase letter (a-z). Contain at least one uppercase letter (A-Z).";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
