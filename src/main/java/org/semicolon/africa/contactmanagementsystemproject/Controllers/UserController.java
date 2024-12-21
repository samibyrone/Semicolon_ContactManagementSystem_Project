package org.semicolon.africa.contactmanagementsystemproject.Controllers;

import lombok.AllArgsConstructor;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.RegisterUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.APIResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.RegisterUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor

public class UserController {

    private final UserService userService;

}
