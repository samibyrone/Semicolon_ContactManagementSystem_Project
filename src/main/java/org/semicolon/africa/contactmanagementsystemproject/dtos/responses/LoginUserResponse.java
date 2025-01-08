package org.semicolon.africa.contactmanagementsystemproject.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class LoginUserResponse {

    @Id
    private String id;
    private String message;
    private String email;
    private boolean isLoggedIn;
}
