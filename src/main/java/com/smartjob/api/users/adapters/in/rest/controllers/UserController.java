package com.smartjob.api.users.adapters.in.rest.controllers;

import com.smartjob.api.users.adapters.in.rest.dtos.request.UserRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserUpdateRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.response.GenericResponse;
import com.smartjob.api.users.application.port.in.UserPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.smartjob.api.users.adapters.in.rest.constants.Messages.SUCCESS;
import static com.smartjob.api.users.adapters.in.rest.constants.Router.BASE_PATH;
import static com.smartjob.api.users.adapters.in.rest.constants.Router.USERS;
import static com.smartjob.api.users.adapters.in.rest.constants.Router.V1;

@RestController
@RequestMapping(BASE_PATH + V1 + USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserPort userPort;

    @PostMapping
    public ResponseEntity<GenericResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        var user = userPort.createUser(userRequest);
        return new ResponseEntity<>(GenericResponse.builder().message(SUCCESS).data(user).build(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updateUser(@PathVariable UUID id,
                                           @Valid @RequestBody UserUpdateRequest userUpdateRequest) {

        var user = userPort.updateUser(id, userUpdateRequest);
        return new ResponseEntity<>(GenericResponse.builder().message(SUCCESS).data(user).build(), HttpStatus.OK);
    }

}
