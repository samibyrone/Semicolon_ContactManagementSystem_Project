package org.semicolon.africa.contactmanagementsystemproject.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class ContactRemoveResponse {

    @Id
    private String contactId;
    private String message;
}
