package com.smartjob.api.users.adapters.out.persistence.sql.adapter;

import com.smartjob.api.users.adapters.in.rest.dtos.request.UserRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserUpdateRequest;
import com.smartjob.api.users.adapters.in.rest.mappers.UserMapper;
import com.smartjob.api.users.adapters.out.persistence.sql.entity.User;
import com.smartjob.api.users.adapters.out.persistence.sql.repository.MySqlRepository;
import com.smartjob.api.users.application.port.in.TokenPort;
import com.smartjob.api.users.application.port.out.DbPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MySqlAdapter implements DbPort<User> {

    private final MySqlRepository mySqlRepository;
    private final TokenPort tokenPort;
    private final BCryptPasswordEncoder pwdEncoder;

    @Override
    public User save(UserRequest userRequest) {
        var user = UserMapper.INSTANCE.toEntity(userRequest);
        user.setEnabled(true);
        user.setToken(tokenPort.generateToken(user.getUsername()));
        user.setPassword(pwdEncoder.encode(user.getPassword()));
        mySqlRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return mySqlRepository.findByUsername(email);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return mySqlRepository.findById(id);
    }

    @Override
    public void update(UserUpdateRequest userUpdateRequest, User user) {
        UserMapper.INSTANCE.toUpdateEntity(userUpdateRequest, user);
        mySqlRepository.save(user);
    }

    @Override
    public void updateLogin(User user) {
        user.setLastLogin(LocalDateTime.now());
        mySqlRepository.save(user);
    }
}
