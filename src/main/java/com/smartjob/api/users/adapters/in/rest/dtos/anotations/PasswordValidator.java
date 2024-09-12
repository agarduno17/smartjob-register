package com.smartjob.api.users.adapters.in.rest.dtos.anotations;

import com.smartjob.api.users.utils.UserValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private final UserValidator userValidator;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String passwordPattern = userValidator.getPasswordRegex();
        return password != null && password.matches(passwordPattern);
    }
}
