package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.data.model.User;
import org.semicolon.africa.contactmanagementsystemproject.data.repository.ContactRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactUpdatesRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapContact;
import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapContactUpdate;

import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRemoveResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactUpdatesResponse;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.ContactAlreadyExisted;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImplementation implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;
    @Qualifier("contactService")
    @Autowired
    private ContactService contactService;


    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(String contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow( () -> new ContactNotFoundException("Contact Does not exist"));
    }

    @Override
    public ContactRegisterResponse createContact(ContactRegisterRequest contactRegisterRequest){
        validateContact(contactRegisterRequest.getContactId());
        Contact contact = new Contact();
        mapContact(contactRegisterRequest, contact);
        contactRepository.save(contact);
        return mapContact(contact);
    }

    private void validateContact(String contactId) {
        boolean contactExisted = contactRepository.existsById(contactId);
            if (contactExisted) throw new ContactAlreadyExisted("Contact Already Existed");
    }

    @Override
    public ContactUpdatesResponse updateContact(String contactId, ContactUpdatesRequest contactUpdatesRequest) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow( () -> new ContactNotFoundException("Contact does not exist"));
        mapContactUpdate(contactUpdatesRequest, contact);
        Contact contactUpdates = contactRepository.save(contact);
        return mapContactUpdate(contactUpdates);
    }

    @Override
    public ContactRemoveResponse removeContact(String contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow( () -> new ContactNotFoundException("Contact Does not exist"));
        contact.setContactId(contactId);
        contactRepository.deleteById(contactId);
        ContactRemoveResponse removeResponse = new ContactRemoveResponse();
        removeResponse.setMessage("Contact Removed Successfully");
        return removeResponse;
    }

}
