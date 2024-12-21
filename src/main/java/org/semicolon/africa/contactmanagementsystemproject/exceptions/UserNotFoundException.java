package org.semicolon.africa.contactmanagementsystemproject.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String response) {
        super(response);
    }
}
