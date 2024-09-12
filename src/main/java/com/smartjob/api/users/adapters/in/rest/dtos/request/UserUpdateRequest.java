package com.smartjob.api.users.adapters.in.rest.dtos.request;

import com.smartjob.api.users.adapters.in.rest.dtos.anotations.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "email is mandatory")
    @ValidEmail
    private String email;

}
