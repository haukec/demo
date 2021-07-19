package com.example.demo.grpc.jwt.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
