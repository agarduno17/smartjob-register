package com.smartjob.api.users.adapters.in.rest.dtos.anotations;

import com.smartjob.api.users.utils.UserValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private final UserValidator userValidator;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String emailPattern = userValidator.getEmailRegex();
        return email != null && email.matches(emailPattern);
    }
}
