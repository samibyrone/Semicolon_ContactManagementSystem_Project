package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRemoveRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactUpdatesRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRemoveResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactUpdatesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContactById(String contactId);

    Contact getContactByFirstName(String firstName, ContactRegisterRequest registerRequest);

    ContactRegisterResponse createContact(ContactRegisterRequest contactRegisterRequest);

    ContactRemoveResponse removeContact(String contactId, ContactRemoveRequest contactRemoveRequest);

    ContactUpdatesResponse updateContact(String contactId, ContactUpdatesRequest contactUpdatesRequest);
}
