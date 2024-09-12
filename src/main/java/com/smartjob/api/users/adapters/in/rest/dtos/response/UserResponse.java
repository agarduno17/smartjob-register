package com.smartjob.api.users.adapters.in.rest.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({ "id", "name", "token", "is_active", "last_login", "created_at", "modified_at" })
@Data
public class UserResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -1251283698655021868L;

    private UUID id;

    private String name;

    private String token;

    @JsonProperty("is_active")
    private boolean isActive;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime lastLogin;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime modifiedAt;

}
