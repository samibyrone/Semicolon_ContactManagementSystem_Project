package org.semicolon.africa.contactmanagementsystemproject.service;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.data.model.User;
import org.semicolon.africa.contactmanagementsystemproject.data.repository.ContactRepository;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRemoveRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactUpdatesRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapContact;
import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapContactUpdate;

import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRemoveResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactUpdatesResponse;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.ContactAlreadyExisted;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.ContactIdNotFoundException;
import org.semicolon.africa.contactmanagementsystemproject.exceptions.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImplementation implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

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
    public Contact getContactByFirstName(String firstName, ContactRegisterRequest registerRequest) {
        return contactRepository.findContactByFirstName(firstName)
                .orElseThrow( () -> new ContactNotFoundException("Contact first name not found"));
    }

    @Override
    public ContactRegisterResponse createContact(ContactRegisterRequest contactRegisterRequest){
        validateContact(contactRegisterRequest.getContactId());
        Contact contact = new Contact();
        mapContact(contactRegisterRequest, contact);
        contactRepository.save(contact);
        System.out.print(contact.getAddress());
        return mapContact(contact);
    }

    @Override
    public ContactRemoveResponse removeContact(String contactId, ContactRemoveRequest contactRemoveRequest) {
        Optional <Contact> findContact = contactRepository.findById(contactId);
        if (!findContact.isPresent()) { throw  new ContactNotFoundException("Contact Does not exist"); }
        contactRepository.deleteById(contactId);
        ContactRemoveResponse removeResponse = new ContactRemoveResponse();
        removeResponse.setMessage("Contact Removed Successfully");
        return removeResponse;
    }

    private void validateContact(String contactId) {
        Optional <User> contactExisted = userService.findUserById(contactId);
        if (contactExisted.isPresent()) throw new ContactAlreadyExisted("Contact Already Existed");
    }

    @Override
    public ContactUpdatesResponse updateContact(String contactId, ContactUpdatesRequest contactUpdatesRequest) {
        validateContact(contactId);
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow( () ->  new ContactNotFoundException("Contact Does not exist"));
        if(contactId == null || contactId.isEmpty()) { throw  new ContactIdNotFoundException("Contact ID is not found"); }
        mapContactUpdate(contactUpdatesRequest, contact);
        Contact updateContact = contactRepository.save(contact);
        return mapContactUpdate(updateContact);
    }
}
