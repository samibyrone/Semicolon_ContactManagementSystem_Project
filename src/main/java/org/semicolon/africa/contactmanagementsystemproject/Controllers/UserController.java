package org.semicolon.africa.contactmanagementsystemproject.Controllers;

import lombok.AllArgsConstructor;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.LoginUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.RegisterUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.LoginUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.RegisterUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?>  register (@RequestBody RegisterUserRequest requestUser){
        RegisterUserResponse userResponse = userService.register(requestUser);
        return ResponseEntity.status(OK).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginUserRequest requestUser){
        LoginUserResponse loginResponse = userService.login(requestUser);
        return ResponseEntity.status(OK).body(loginResponse);
    }
}
