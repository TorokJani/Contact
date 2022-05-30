package com.tanos.contacts.rest.hateoes;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.service.ContactService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api-v2")
public class ContactRestHateoasController {

    private ContactService contactService;

    public ContactRestHateoasController(ContactService contactService) {
        this.contactService = contactService;
    }

    /*@GetMapping
    public List<Contact> getContacts(){

    }*/

    @GetMapping("/contacts/{id}")
    public HttpEntity<EntityModel<Contact>> getContact(@PathVariable Long id){
        Contact c = contactService.findById(id);
        if (c== null){
            return ResponseEntity.notFound().build();
        }

        EntityModel <Contact> contactResource = EntityModel.of(c);
        //contactResource.add(Link.of("http://localhost:8080/api-v2/contacts/" + id,"custom"));
        //contactResource.add(linkTo(ContactRestHateoasController.class).withSelfRel());
        contactResource.add(linkTo(methodOn(ContactRestHateoasController.class).getContact(id)).withSelfRel());
        return ResponseEntity.ok(contactResource);
    }
}
