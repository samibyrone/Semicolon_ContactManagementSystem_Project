package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.data.repository.ContactRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapContact;

import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRemoveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactServiceImplementation implements ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public ContactRegisterResponse createContact(ContactRegisterRequest contactRegisterRequest){
        Contact contact = new Contact();
        mapContact(contactRegisterRequest, contact);
        Contact newContact = contactRepository.save(contact);
        return mapContact(newContact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


    @Override
    public ContactRemoveResponse removeContact(String contactId) {
        return null;
    }

}
