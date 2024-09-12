package com.smartjob.api.users.adapters.in.rest.controllers;

import com.smartjob.api.users.adapters.in.rest.dtos.request.LoginRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.response.GenericResponse;
import com.smartjob.api.users.application.port.in.UserPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.smartjob.api.users.adapters.in.rest.constants.Messages.SUCCESS;
import static com.smartjob.api.users.adapters.in.rest.constants.Router.BASE_PATH;
import static com.smartjob.api.users.adapters.in.rest.constants.Router.LOGIN;
import static com.smartjob.api.users.adapters.in.rest.constants.Router.V1;


@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_PATH + V1 + LOGIN)
public class LoginController {

    private final UserPort userPort;

    @PostMapping()
    public ResponseEntity<GenericResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        var token = userPort.loginUser(loginRequest);
        return new ResponseEntity<>(GenericResponse.builder().message(SUCCESS).data(token).build(), HttpStatus.OK);
    }
}
