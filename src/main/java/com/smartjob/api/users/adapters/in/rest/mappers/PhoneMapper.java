package com.smartjob.api.users.adapters.in.rest.mappers;

import com.smartjob.api.users.adapters.in.rest.dtos.request.PhoneRequest;
import com.smartjob.api.users.adapters.in.rest.dtos.response.PhoneResponse;
import com.smartjob.api.users.adapters.out.persistence.sql.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @Mapping(target = "user", ignore = true)
    Phone toEntity(PhoneRequest phoneRequest);

    PhoneResponse toResponse(Phone phone);
}
