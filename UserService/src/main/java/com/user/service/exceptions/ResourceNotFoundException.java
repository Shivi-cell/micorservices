package com.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found with the given Id");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
