package org.semicolon.africa.contactmanagementsystemproject.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class ContactRegisterRequest {

    @Id
    private String contactId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
}
