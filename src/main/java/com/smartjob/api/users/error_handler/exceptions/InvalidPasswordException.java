package com.smartjob.api.users.error_handler.exceptions;

import java.io.Serial;

public class InvalidPasswordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidPasswordException(String msg) {
        super(msg);
    }

}
