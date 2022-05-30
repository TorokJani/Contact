package com.tanos.contacts.rest;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.service.ContactService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactRestController {

    private ContactService contactService;

    public ContactRestController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/contacts",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contact> contacts(){
        return contactService.getContacts();
    }

    @GetMapping(value= "/contacts/{id}")
    public HttpEntity<Contact> getContactById(@PathVariable Long id){
        Contact contact = contactService.findById(id);
        if (contact == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @PostMapping(value = "/contacts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Contact> createContatct(@Valid @RequestBody Contact contact ){
        try{
            Contact result = contactService.saveContact(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }


}
