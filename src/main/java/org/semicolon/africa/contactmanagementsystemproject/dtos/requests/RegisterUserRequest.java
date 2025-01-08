package org.semicolon.africa.contactmanagementsystemproject.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
}


