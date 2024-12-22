package org.semicolon.africa.contactmanagementsystemproject.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class ContactRemoveRequest {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
