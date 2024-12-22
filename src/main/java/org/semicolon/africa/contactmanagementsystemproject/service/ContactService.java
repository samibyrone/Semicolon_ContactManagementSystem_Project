package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRemoveRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRemoveResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    ContactRegisterResponse createContact(ContactRegisterRequest contactRegisterRequest);

    List<Contact> getAllContacts();
    
    ContactRemoveResponse removeContact(String contactId);

    Object getContactById(String contactId);
}
