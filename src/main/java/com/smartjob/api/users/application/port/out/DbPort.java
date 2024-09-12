package com.smartjob.api.users.application.port.out;

import com.smartjob.api.users.adapters.in.rest.dtos.request.UserRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserUpdateRequest;
import com.smartjob.api.users.adapters.out.persistence.sql.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface DbPort<T> {
    T save(UserRequest userRequest);

    Optional<T> findByUsername(String email);

    Optional<T> findById(UUID id);

    void update(UserUpdateRequest userUpdateRequest, User user);

    void updateLogin(User user);
}
