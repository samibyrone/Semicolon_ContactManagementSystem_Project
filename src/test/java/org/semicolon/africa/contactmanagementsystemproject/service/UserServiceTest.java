package org.semicolon.africa.contactmanagementsystemproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semicolon.africa.contactmanagementsystemproject.data.repository.UserRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.LoginUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.RegisterUserRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.LoginUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.RegisterUserResponse;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.EmailAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testThatUserCanBeRegistered() {
        RegisterUserResponse response = registerUser();
        assertThat(response).isNotNull();
        assertThat(userService.getAllUsers()).size().isEqualTo(1L);
        assertThat(response.getMessage()).contains("Successfully registered");
    }

    private RegisterUserResponse registerUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFirstName("samson");
        request.setLastName("danny");
        request.setEmail("sam@gmail.com");
        request.setPassword("12345");
        request.setAddress("302, sabo-yaba lagos");
        RegisterUserResponse response = userService.register(request);
        return response;
    }

    @Test
    public void testThatUserCanNotRegister2TimesWithTheSameEmail() {
        registerUser();
        RegisterUserRequest request2 = new RegisterUserRequest();
        request2.setFirstName("Babatunde");
        request2.setLastName("Olanrewaju");
        request2.setEmail("sam@gmail.com");
        request2.setPassword("54321");
        request2.setAddress("302, alagomeji, bus-stop lagos");
        assertThrows(EmailAlreadyExist.class, () -> userService.register(request2));
        assertThat(userService.getAllUsers()).size().isEqualTo(1L);
    }

    @Test
    public void testThatAUserCanLogIn() {
        registerUser();
        LoginUserRequest userLogin = new LoginUserRequest();
        userLogin.setEmail("sam@gmail.com");
        userLogin.setPassword("12345");
        LoginUserResponse loginResponse = userService.login(userLogin);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).contains("Successfully logged in");
        assertThat(loginResponse.isLoggedIn()).isTrue();
    }

    @Test
    public void testThat2UserCanRegister() {
        registerUser();
        RegisterUserRequest request2 = new RegisterUserRequest();
        request2.setFirstName("Tunde");
        request2.setLastName("Olanrewaju");
        request2.setEmail("sammmy@gmail.com");
        request2.setPassword("54321");
        request2.setAddress("302, alagomeji, bus-stop lagos");
        RegisterUserResponse response = userService.register(request2);
        assertThat(response).isNotNull();
        assertThrows(EmailAlreadyExist.class, () -> userService.register(request2));
        assertThat(userService.getAllUsers()).size().isEqualTo(2L);
    }
}