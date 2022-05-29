package com.tanos.contacts.repository;

import com.tanos.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

public Contact findByEmailAndCreatedAt(String email, Date createdAt);

/* public class ContactRepository {
private List<Contact> contacts = new ArrayList<>();

    public void deleteContact(UUID id) {
        contacts.removeIf(contact -> contact.getId().equals(id));
    }

    public void addContact(Contact c){
        contacts.add(c);
    }

    public List<Contact> getContacts(){
        return contacts;
    }


    public Contact findById(UUID id) {
        return contacts.stream().filter(contact -> contact.getId().equals(id)).findFirst().get();
    }

    public void updateContact(Contact c) {
        Contact existing = contacts.stream().filter(contact -> contact.getId().equals(c.getId())).findFirst().get();
        existing.setName(c.getName());
        existing.setPhone(c.getPhone());
        existing.setAddress(c.getAddress());
        existing.setBirthDate(c.getBirthDate());
        existing.setEmail(c.getEmail());
    }*/


}
