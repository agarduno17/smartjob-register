package com.smartjob.api.users.adapters.in.rest.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenericResponse {
    private String   message;
    private Object data;
}
