package org.semicolon.africa.contactmanagementsystemproject.Controllers;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactUpdatesRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactUpdatesResponse;
import org.semicolon.africa.contactmanagementsystemproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.semicolon.africa.contactmanagementsystemproject.utils.Mapper.mapContact;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactRegisterResponse> getContactById(@PathVariable String contactId) {
        var contact = contactService.getContactById(contactId);
        ContactRegisterResponse contactResponse = mapContact(contact);
        return ResponseEntity.status(OK).body(contactResponse);
    }

    @PostMapping
    public ResponseEntity<ContactRegisterResponse> createContact(@RequestBody ContactRegisterRequest contact) {
        ContactRegisterResponse newContact = contactService.createContact(contact);
        return ResponseEntity.status(OK).body(newContact);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactUpdatesResponse> updateContact(@PathVariable String contactId, @RequestBody ContactUpdatesRequest contactUpdate) {
        ContactUpdatesResponse contactResponse = contactService.updateContact(contactId, contactUpdate);
        return ResponseEntity.status(OK).body(contactResponse);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable String contactId) {
        contactService.removeContact(contactId);
        return ResponseEntity.status(OK).build();
    }
}


