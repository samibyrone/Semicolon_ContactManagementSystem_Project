package org.semicolon.africa.contactmanagementsystemproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
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
        System.out.println("Updating contact with ID: " + contactResponse);
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
        contactRequest.setPhoneNumber("08012345678");
        return contactService.createContact(contactRequest);
    }

    @Test
    public void testThatContactSavedCanBeUpdated() {
       ContactRegisterResponse contactResponse = contactRegister();
        String contactId = contactResponse.getContactId();
        ContactUpdatesRequest updateRequest = new ContactUpdatesRequest();
        updateRequest.setFirstName("Johnson");
        updateRequest.setLastName("Dior");
        updateRequest.setEmail("johnson@.com");
        updateRequest.setAddress("Lagos, onike sabo-yaba");
        updateRequest.setPhone("08064387215");
        ContactUpdatesResponse updateResponse = contactService.updateContact(contactId, updateRequest);
        System.out.println("Updating contact with ID: " + updateResponse);
        assertThat(updateResponse).isNotNull();
        assertThat(updateResponse.getMessage()).contains("Contact Was Successfully Updated");
        assertThat(contactService.getAllContacts().size()).isEqualTo(1L);

//        Contact contactRequest = new Contact();
//        contactRequest.setFirstName("John");
//        contactRequest.setLastName("Doe");
//        contactRequest.setUserName("johnDoe");
//        contactRequest.setEmail("john@doe.com");
//        contactRequest.setAddress("Lagos, Yaba");
//        contactRequest.setPhoneNumber("08012345678");
//        Contact savedContact = contactService.createContact(contactRequest);
//        String contactId = String.valueOf(contactRepository.findById(String.valueOf(savedContact.getContactId())));
//        System.out.println("Updating contact with ID: " + contactId); // Debugging statement
    }

    @Test
    public void testThatContactSavedCanBeDeleted() {
        ContactRegisterResponse removeResponse = contactRegister();
        String contactId = removeResponse.getContactId();
        ContactRemoveRequest contactRemove = new ContactRemoveRequest();
        contactRemove.setFirstName("john");
        contactRemove.setLastName("Doe");
        contactRemove.setEmail("john@doe.com");
        contactRemove.setAddress("Lagos, yaba");
        contactRemove.setPhone("08012345678");
        ContactRemoveResponse removedResponse = contactService.removeContact(contactId, contactRemove);
        System.out.println("Updating contact with ID: " + removedResponse);
        assertThat(removedResponse).isNotNull();
        assertThat(removedResponse.getMessage()).contains("Contact Removed Successfully");
        assertThat(contactService.getAllContacts().size()).isEqualTo(0);
    }
}

