package org.semicolon.africa.contactmanagementsystemproject.dtos.responses;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class LoginUserResponse {

    private String message;
    private String email;
    private boolean isLoggedIn;
}
