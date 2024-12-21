package org.semicolon.africa.contactmanagementsystemproject.exceptions;

public class EmailAlreadyExist extends RuntimeException {
    public EmailAlreadyExist(String response) {
        super(response);
    }
}
