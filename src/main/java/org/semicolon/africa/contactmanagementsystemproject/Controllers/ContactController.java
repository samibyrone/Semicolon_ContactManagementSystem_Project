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

    @GetMapping("/getContactById")
    public ResponseEntity<?> getContactById(@RequestBody ContactRegisterRequest registerRequest) {
        ContactRegisterResponse contactResponse = contactService.getContactById(registerRequest);
        return ResponseEntity.status(OK).body(contactResponse);
    }

    @GetMapping("/getContactByFirstName")
    public ResponseEntity<?> getContactByFirstName(@PathVariable String firstName, @RequestBody ContactRegisterRequest registerRequest) {
//        var contact = contactService.getContactByFirstName(firstName);
        ContactRegisterResponse contactResponse = contactService.getContactByFirstName(firstName, registerRequest);
        return ResponseEntity.status(OK).body(contactResponse);
    }

    @PostMapping("/createContact")
    public ResponseEntity<?> createContact(@RequestBody ContactRegisterRequest newContact) {
        ContactRegisterResponse contacts = contactService.createContact(newContact);
        return ResponseEntity.status(OK).body(contacts);
    }

    @PutMapping("/updateContact")
    public ResponseEntity<?> updateContact(@PathVariable String contactId, @RequestBody ContactUpdatesRequest contactUpdate) {
        ContactUpdatesResponse contactResponse = contactService.updateContact(contactId, contactUpdate);
        return ResponseEntity.status(OK).body(contactResponse);
    }

    @DeleteMapping("/deleteContact")
    public ResponseEntity<?> deleteContact(@PathVariable String contactId, @RequestBody ContactRemoveRequest contactDelete) {
        ContactRemoveResponse deleteResponse = contactService.removeContact(contactId, contactDelete);
        return ResponseEntity.status(OK).body(deleteResponse);
    }
}

