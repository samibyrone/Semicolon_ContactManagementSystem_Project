package org.semicolon.africa.contactmanagementsystemproject.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class ContactUpdatesRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
}
