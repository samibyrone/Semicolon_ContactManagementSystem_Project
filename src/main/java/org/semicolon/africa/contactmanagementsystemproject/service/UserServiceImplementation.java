package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.User;
import org.semicolon.africa.contactmanagementsystemproject.data.repository.UserRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.LoginUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.RegisterUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.LoginUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.RegisterUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.EmailAlreadyExist;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.map;
import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapLogin;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        validateExistingEmail(registerUserRequest.getEmail());
        User user = new User();
        map(registerUserRequest, user);
        userRepository.save(user);
        return map(user);
    }

    private void validateExistingEmail(String email) {
        boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) throw new EmailAlreadyExist("Email Already Exist");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public LoginUserResponse login(LoginUserRequest userLogin) {
        User user = findByEmail(userLogin.getEmail());
        mapLogin(user);
        user.setLoggedIn(true);
        userRepository.save(user);
        return mapLogin(user);
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow( () -> new UserNotFoundException("User does not exist"));
    }

}
