package org.semicolon.africa.contactmanagementsystemproject.data.repository;

import org.semicolon.africa.contactmanagementsystemproject.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

    boolean existsById(String contactId);

    Optional <Contact> findById(String contactId);
}
