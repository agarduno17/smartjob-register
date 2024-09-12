package com.smartjob.api.users.error_handler.exceptions;

import java.io.Serial;

public class EmailAlreadyExistsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
