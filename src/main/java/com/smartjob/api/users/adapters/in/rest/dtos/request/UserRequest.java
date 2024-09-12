package com.smartjob.api.users.adapters.in.rest.dtos.request;

import com.smartjob.api.users.adapters.in.rest.dtos.anotations.ValidEmail;
import com.smartjob.api.users.adapters.in.rest.dtos.anotations.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "email is mandatory")
    @ValidEmail
    private String email;

    @NotBlank(message = "password is mandatory")
    @ValidPassword
    private String password;

    @NotEmpty(message = "phones is mandatory")
    private Set<PhoneRequest> phones;
}
