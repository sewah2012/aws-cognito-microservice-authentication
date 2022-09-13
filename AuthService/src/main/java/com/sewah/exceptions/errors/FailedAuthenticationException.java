package com.sewah.exceptions.errors;

public class FailedAuthenticationException extends RuntimeException {
    public FailedAuthenticationException(String message){
        super(message);
    }
}
