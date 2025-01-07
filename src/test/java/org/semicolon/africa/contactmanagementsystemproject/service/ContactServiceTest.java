package org.semicolon.africa.contactmanagementsystemproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.semicolon.africa.contactmanagementsystemproject.data.repository.ContactRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRemoveRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactUpdatesRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRemoveResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactUpdatesResponse;
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
        ContactRegisterResponse contactResponse = contactRegister();
        assertThat(contactResponse).isNotNull();
        assertThat(contactResponse.getMessage()).contains("Contact Was Successfully Created!");
        assertThat(contactService.getAllContacts().size()).isEqualTo(1L);
    }

    private ContactRegisterResponse contactRegister() {
        ContactRegisterRequest contactRequest = new ContactRegisterRequest();
        contactRequest.setFirstName("John");
        contactRequest.setLastName("Doe");
        contactRequest.setUserName("johnDoe");
        contactRequest.setEmail("john@doe.com");
        contactRequest.setAddress("Lagos, Yaba");
        contactRequest.setPhone("08012345678");
        return contactService.createContact(contactRequest);
    }

    @Test
    public void testThatContactSavedCanBeUpdated() {
        ContactRegisterResponse updatesResponse = contactRegister();
        String contactId = updatesResponse.getContactId();
        ContactUpdatesRequest updateRequest = new ContactUpdatesRequest();
        updateRequest.setFirstName("Johnson");
        updateRequest.setLastName("Dior");
        updateRequest.setEmail("johnson@.com");
        updateRequest.setAddress("Lagos, onike sabo-yaba");
        updateRequest.setPhone("08064387215");
        ContactUpdatesResponse updateResponse = contactService.updateContact(contactId, updateRequest);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getMessage()).contains("Successfully Was Successfully Updated");
        assertThat(contactService.getAllContacts().size()).isEqualTo(1L);
    }

    @Test
    public void testThatContactSavedCanBeDeleted() {
        ContactRegisterResponse removeResponse = contactRegister();
        String contactId = removeResponse.getContactId();
        ContactRemoveRequest contactRemove = new ContactRemoveRequest();
//        contactRemove.setContactId("952");
        contactRemove.setFirstName("john");
        contactRemove.setLastName("Doe");
        contactRemove.setEmail("john@doe.com");
        contactRemove.setAddress("Lagos, yaba");
        contactRemove.setPhone("08012345678");
        ContactRemoveResponse removedResponse = contactService.removeContact(contactId, contactRemove);
        assertThat(removedResponse).isNotNull();
        assertThat(removedResponse.getMessage()).contains("Contact Removed Successfully");
        assertThat(contactService.getAllContacts().size()).isEqualTo(0);
    }
}
