package com.user.service.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(){
        super("this is a test message");
    }

    public ApiException(String message){
        super(message);
    }
}
