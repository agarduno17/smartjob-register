package com.smartjob.api.users.adapters.in.rest.mappers;

import com.smartjob.api.users.adapters.in.rest.dtos.request.UserRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.request.UserUpdateRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.response.UserUpdateResponse;
import com.smartjob.api.users.adapters.in.rest.dtos.response.UserResponse;
import com.smartjob.api.users.adapters.out.persistence.sql.entity.Phone;
import com.smartjob.api.users.adapters.out.persistence.sql.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "email", target = "username")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "user.created", ignore = true)
    @Mapping(target = "user.modified", ignore = true)
    User toEntity(UserRequest userRequest);

    @AfterMapping
    default void linkPhonesToUser(@MappingTarget User user) {
        if (user.getPhones() != null) {
            Set<Phone> phones = new HashSet<>(user.getPhones());
            for (Phone phone : phones) {
                user.addPhone(phone);
            }
        }
    }

    @Mapping(source = "enabled", target = "active")
    UserResponse toResponse(User user);

    @Mapping(source = "enabled", target = "active")
    @Mapping(source = "username", target = "email")
    UserUpdateResponse toUpdateResponse(User user);

    @Mapping(source = "email", target = "username")
    @Mapping(target = "phones", ignore = true)
    void toUpdateEntity(UserUpdateRequest userUpdateRequest, @MappingTarget User user);



}
