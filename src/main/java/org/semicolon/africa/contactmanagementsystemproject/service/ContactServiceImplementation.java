package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.data.repository.ContactRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.CreateContactRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.CreateContactResponse;
import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapContact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactServiceImplementation implements ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public CreateContactResponse createContact(CreateContactRequest createContactRequest){
        Contact contact = new Contact();
        mapContact(createContactRequest, contact);
        Contact newContact = contactRepository.save(contact);
        return mapContact(newContact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

}
