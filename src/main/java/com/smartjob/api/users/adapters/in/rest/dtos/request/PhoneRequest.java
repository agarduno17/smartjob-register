package com.smartjob.api.users.adapters.in.rest.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PhoneRequest {

    @NotBlank(message = "phone number is mandatory")
    private String number;

    @NotBlank(message = "city_code is mandatory")
    private String cityCode;

    @NotBlank(message = "country_code is mandatory")
    private String countryCode;
}
