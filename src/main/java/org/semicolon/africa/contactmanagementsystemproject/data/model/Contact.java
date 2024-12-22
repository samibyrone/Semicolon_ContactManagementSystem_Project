package org.semicolon.africa.contactmanagementsystemproject.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Contact {

    @Id
    private String contactId;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String email;
    private String address;
}
