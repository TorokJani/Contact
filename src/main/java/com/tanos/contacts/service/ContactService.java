package com.tanos.contacts.service;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void addContact(Contact c) {
        c.setId((UUID.randomUUID()));
        contactRepository.save(c);
        log.info("contact add "+ c.toString());
       // contactRepository.addContact(c);
    }

    //public List<Contact> getContacts() {        return contactRepository.getContacts();    }
    public List<Contact> getContacts() {return contactRepository.findAll();}

    //public void deleteContact(UUID id) {        contactRepository.deleteContact(id);    }
    public void deleteContact(UUID id) {        contactRepository.deleteById(id);    }

    //public Contact findById(UUID id) {    return contactRepository.findById(id);    }
    public Contact findById(UUID id) {    return contactRepository.findById(id).orElse(null);    }

    //public void updateContact(Contact contact) {    contactRepository.updateContact(contact);    }

    public void updateContact(Contact contact) {    contactRepository.save(contact);    }

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }
}
