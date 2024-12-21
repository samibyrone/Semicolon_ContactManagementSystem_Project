package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.User;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.LoginUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.RegisterUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.LoginUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.RegisterUserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  UserService {
    RegisterUserResponse register (RegisterUserRequest registerUserRequest);

    List<User> getAllUsers();

    LoginUserResponse login(LoginUserRequest userLogin);
}
