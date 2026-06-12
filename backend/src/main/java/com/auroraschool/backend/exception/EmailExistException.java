package com.auroraschool.backend.exception;

/**
 * Exception thrown when trying to register a user with an email that already exists in the database
 * @author Driton Jasiqi
 */
public class EmailExistException extends IllegalArgumentException {
    public EmailExistException(String message) {
        super(message);
    }
}
