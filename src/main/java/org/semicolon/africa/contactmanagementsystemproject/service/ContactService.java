package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.CreateContactRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.CreateContactResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    CreateContactResponse createContact(CreateContactRequest createContactRequest);

    List<Contact> getAllContacts();
}
