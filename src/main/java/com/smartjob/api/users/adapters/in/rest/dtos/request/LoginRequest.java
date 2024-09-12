package com.smartjob.api.users.adapters.in.rest.dtos.request;

import com.smartjob.api.users.adapters.in.rest.dtos.anotations.ValidEmail;
import com.smartjob.api.users.adapters.in.rest.dtos.anotations.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "email is mandatory")
    @ValidEmail
    private String email;

    @NotBlank(message = "password is mandatory")
    @ValidPassword
    private String password;
}
