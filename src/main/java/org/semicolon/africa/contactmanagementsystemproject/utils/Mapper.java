package org.semicolon.africa.contactmanagementsystemproject.utils;

import org.apache.coyote.Request;
import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.data.model.User;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.CreateContactRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.RegisterUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.CreateContactResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.LoginUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.RegisterUserResponse;

public class Mapper {

    public static void map(RegisterUserRequest registerUserRequest, User user) {
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPassword());
        user.setAddress(registerUserRequest.getAddress());
        user.setPhoneNumber(registerUserRequest.getPhoneNumber());
    }

    public static RegisterUserResponse map(User user) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Successfully registered!");
        registerUserResponse.setEmail(user.getEmail());
        return registerUserResponse;
    }

    public static LoginUserResponse mapLogin(User user) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        user.setEmail(user.getEmail());
        loginUserResponse.setMessage("Successfully logged in!");
        loginUserResponse.setLoggedIn(true);
        return loginUserResponse;
    }

    public static void mapContact(CreateContactRequest contactRequest, Contact contact) {
        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());
        contact.setEmail(contactRequest.getEmail());
        contact.setAddress(contactRequest.getAddress());
        contact.setPhoneNumber(contactRequest.getPhone());
    }

    public static CreateContactResponse mapContact(Contact contact) {
        CreateContactResponse createContactResponse = new CreateContactResponse();
        createContactResponse.setMessage("Successfully created!");
        createContactResponse.setId(contact.getId());
        return createContactResponse;
    }
}
