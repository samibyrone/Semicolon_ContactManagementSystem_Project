package org.semicolon.africa.contactmanagementsystemproject.Controllers;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRegisterRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactRemoveRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.requests.ContactUpdatesRequest;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRegisterResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactRemoveResponse;
import org.semicolon.africa.contactmanagementsystemproject.dtos.responses.ContactUpdatesResponse;
import org.semicolon.africa.contactmanagementsystemproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v2/Contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/getContactById/{contactId}")
    public ResponseEntity<?> getContactById(@PathVariable("contactId") String contactId) {
        Contact contactResponse = contactService.getContactById(contactId);
        return ResponseEntity.status(HttpStatus.OK).body(contactResponse);
    }

    @GetMapping("/getContactByFirstName/{firstName}")
    public ResponseEntity<?> getContactByFirstName(@PathVariable("firstName") String firstName, @RequestBody ContactRegisterRequest registerRequest) {
        Contact contactResponse = contactService.getContactByFirstName(firstName, registerRequest);
        return ResponseEntity.status(OK).body(contactResponse);
    }

    @PostMapping("/createContact")
    public ResponseEntity<?> createContact(@RequestBody ContactRegisterRequest newContact) {
        ContactRegisterResponse contacts = contactService.createContact(newContact);
        return ResponseEntity.status(OK).body(contacts);
    }

    @PutMapping("/updateContact/{contactId}")
    public ResponseEntity<?> updateContact(@PathVariable("contactId") String contactId, @RequestBody ContactUpdatesRequest contactUpdate) {
        ContactUpdatesResponse contactResponse = contactService.updateContact(contactId, contactUpdate);
        return ResponseEntity.status(OK).body(contactResponse);
    }

    @DeleteMapping("/deleteContact/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable("contactId") String contactId, @RequestBody ContactRemoveRequest contactDelete) {
        ContactRemoveResponse deleteResponse = contactService.removeContact(contactId, contactDelete);
        return ResponseEntity.status(OK).body(deleteResponse);
    }
}

