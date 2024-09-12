package com.smartjob.api.users.adapters.in.rest.dtos.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class PhoneResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 5958107032687649525L;

    private String number;

    private String cityCode;

    private String countryCode;
}
