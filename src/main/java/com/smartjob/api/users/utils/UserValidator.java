package com.smartjob.api.users.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserValidator {
    @Value("${user.password.regex}")
    private String passwordRegex;

    @Value("${user.email.regex}")
    private String emailRegex;
}
