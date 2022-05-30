package com.tanos.contacts.rest.hateoes;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.rest.hateoes.assembler.ContactResourceAssembler;
import com.tanos.contacts.rest.hateoes.resources.ContactResource;
import com.tanos.contacts.service.ContactService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api-v2")
public class ContactRestHateoasController {

    private ContactService contactService;

    public ContactRestHateoasController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public HttpEntity<CollectionModel<EntityModel<Contact>>>getContacts(){
        List<Contact> contacts = contactService.getContacts();
        CollectionModel<EntityModel<Contact>> contactsResource = CollectionModel.wrap(contacts);

        contactsResource.add(linkTo(methodOn(ContactRestHateoasController.class).getContacts()).withSelfRel());
        return ResponseEntity.ok(contactsResource);
    }

    @GetMapping("/contacts/{id}")
    public HttpEntity<EntityModel<ContactResource>> getContact(@PathVariable Long id){
        Contact c = contactService.findById(id);
        if (c== null){
            return ResponseEntity.notFound().build();
        }

        EntityModel<ContactResource> contactResource = EntityModel.of(new ContactResourceAssembler().toModel(c));

        //EntityModel <Contact> contactResource = EntityModel.of(c);
          //contactResource.add(Link.of("http://localhost:8080/api-v2/contacts/" + id,"custom"));
          //contactResource.add(linkTo(ContactRestHateoasController.class).withSelfRel());
        //contactResource.add(linkTo(methodOn(ContactRestHateoasController.class).getContact(id)).withSelfRel());
        //contactResource.add(linkTo(methodOn(ContactRestHateoasController.class).updateContact(c.getId(),c)).withRel("update"));

        return ResponseEntity.ok(contactResource);
    }

    @PutMapping("/contacts/{id}")
    public HttpEntity<EntityModel<Contact>> updateContact(@PathVariable Long id, @RequestBody Contact contact){
        return ResponseEntity.notFound().build();
    }
}
