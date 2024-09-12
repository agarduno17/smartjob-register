package com.smartjob.api.users.application.port.in;

public interface TokenPort {

    String generateToken(String email);

    boolean validateToken(String token);
}
