package org.semicolon.africa.contactmanagementsystemproject.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class ContactRegisterResponse {

    @Id
    private String contactId;
    private String message;
}
