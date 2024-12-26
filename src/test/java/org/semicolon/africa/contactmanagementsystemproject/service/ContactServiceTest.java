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
        ContactRegisterResponse contactResponse = ContactRegister();
        assertThat(contactResponse).isNotNull();
        assertThat(contactResponse.getMessage()).contains("Contact Was Successfully Created!");
        assertThat(contactService.getAllContacts()).isEqualTo (1L);
    }

    private ContactRegisterResponse ContactRegister() {
        ContactRegisterRequest contactRequest = new ContactRegisterRequest();
        contactRequest.setContactId("542");
        contactRequest.setFirstName("John");
        contactRequest.setLastName("Doe");
        contactRequest.setEmail("john@doe.com");
        contactRequest.setAddress("Lagos, Yaba");
        contactRequest.setPhone("080");
        ContactRegisterResponse contactResponse = contactService.createContact(contactRequest);
        return contactResponse;
    }

    @Test
    public void testThatContactSavedCanBeUpdated() {
        ContactRegister();
        ContactUpdatesRequest contactUpdate = new ContactUpdatesRequest();
        contactUpdate.setFirstName("Johnson");
        contactUpdate.setLastName("Dior");
        contactUpdate.setEmail("johnson@.com");
        contactUpdate.setAddress("Lagos, onike sabo-yaba");
        contactUpdate.setPhone("08064387215");
        ContactUpdatesResponse updatesResponse = contactService.updateContact("542", contactUpdate);
        assertThat(updatesResponse).isNotNull();
        assertThat(updatesResponse.getMessage()).contains("Contact Was Successfully Created");
        assertThat(contactService.getAllContacts()).isEqualTo(1L);
    }

    @Test
    public void testThatContactSavedCanBeDeleted() {
        ContactRegister();
        ContactRemoveRequest contactRemove = new ContactRemoveRequest();
        contactRemove.setContactId("952");
        contactRemove.setFirstName("Kent");
        contactRemove.setLastName("Donald");
        contactRemove.setEmail("kent@donald.com");
        contactRemove.setAddress("Lagos, Festac-town");
        contactRemove.setPhone("08099934210");
        ContactRemoveResponse removeResponse = contactService.removeContact("952");
        assertThat(removeResponse).isNotNull();
        assertThat(removeResponse.getMessage()).contains("Contact Was Successfully Removed");
        assertThat(contactService.getAllContacts()).isEqualTo(0);
    }
}
