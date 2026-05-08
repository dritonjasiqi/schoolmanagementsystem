package com.auroraschool.backend.exception;

public class EmailExistException extends IllegalArgumentException {
    public EmailExistException(String message) {
        super(message);
    }
}
