package com.smartjob.api.users.application.service;

import com.smartjob.api.users.adapters.in.rest.dtos.request.LoginRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserUpdateRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.response.UserUpdateResponse;
import com.smartjob.api.users.adapters.in.rest.dtos.response.UserResponse;
import com.smartjob.api.users.adapters.in.rest.mappers.UserMapper;
import com.smartjob.api.users.adapters.out.persistence.sql.entity.User;
import com.smartjob.api.users.application.port.in.UserPort;
import com.smartjob.api.users.application.port.out.DbPort;
import com.smartjob.api.users.error_handler.exceptions.EmailAlreadyExistsException;
import com.smartjob.api.users.error_handler.exceptions.InvalidPasswordException;
import com.smartjob.api.users.error_handler.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserPort {

    private final DbPort<User> dbPort;
    private final BCryptPasswordEncoder pwdEncoder;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        getUserByEmail(userRequest.getEmail())
                .ifPresent((user -> {
                    throw new EmailAlreadyExistsException("Email " + user.getUsername() + " already register");
                }));
        var user = dbPort.save(userRequest);
        return UserMapper.INSTANCE.toResponse(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return dbPort.findByUsername(email);
    }

    public Optional<User> isUserExists(UUID id) {
        return dbPort.findById(id);
    }


    @Override
    public UserUpdateResponse updateUser(UUID id, UserUpdateRequest userUpdateRequest) {
        User user = isUserExists(id)
                .orElseThrow(() -> new ResourceNotFoundException("Username with id " + id + " not exists"));
        dbPort.update(userUpdateRequest, user);
        return UserMapper.INSTANCE.toUpdateResponse(user);
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        User user = getUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Username or Email " + loginRequest.getEmail() + " not register"));
        var isValidPwd = pwdEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!isValidPwd) {
            throw new InvalidPasswordException("Password does not match");
        }
        dbPort.updateLogin(user);
        return user.getToken();
    }
}
