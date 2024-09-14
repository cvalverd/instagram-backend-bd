package com.fullstack.instagram_backend_bd.exceptionhandler;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
 }