package com.smartjob.api.users.application.port.in;


import com.smartjob.api.users.adapters.in.rest.dtos.request.LoginRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserUpdateRequest;

import java.util.Optional;
import java.util.UUID;

public interface UserPort {
    <T> T createUser(UserRequest userRequest);

    <T> Optional<T> getUserByEmail(String email);

    <T> T updateUser(UUID id, UserUpdateRequest userUpdateRequest);

    <T> T loginUser(LoginRequest loginRequest);
}
