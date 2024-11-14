package com.simplebank.simplebankapp.service.exception;

public class DniAlreadyExistsException extends RuntimeException{
    public DniAlreadyExistsException(String message) {
        super(message);
    }
}
