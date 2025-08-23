package com.example.yoda.Chapter21.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        //now we can return any message that are thrown by controller class
        super(message);
    }
}
