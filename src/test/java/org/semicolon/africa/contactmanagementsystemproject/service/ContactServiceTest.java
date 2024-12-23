package org.semicolon.africa.contactmanagementsystemproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.semicolon.africa.contactmanagementsystemproject.data.repository.ContactRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        contactRepository.deleteAll();
    }

    @Test
    public void testThatContactCanBeAdded() {
        ContactRegisterRequest contactRequest = new ContactRegisterRequest();
        contactRequest.setFirstName("John");
        contactRequest.setLastName("Doe");
        contactRequest.setEmail("john@doe.com");
        contactRequest.setAddress("Lagos, Yaba");
        contactRequest.setPhone("080");
        ContactRegisterResponse contactResponse = contactService.createContact(contactRequest);
        assertThat(contactResponse).isNotNull();
        assertThat(contactResponse.getMessage()).contains("Successfully created!");
//        assertThat(contactService.getAllContacts()).isEqualTo (1L);
    }


}
