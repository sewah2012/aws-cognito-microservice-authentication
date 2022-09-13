package com.sewah.exceptions.errors;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String msg){
        super(msg);
    }
}
