package com.tanos.contacts.rest;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.model.ContactGroup;
import com.tanos.contacts.model.hateoas.assemblers.ContactResourceAssembler;
import com.tanos.contacts.model.hateoas.resources.ContactResource;
import com.tanos.contacts.repository.ContactGroupRepository;
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
@RequestMapping("/api-v2/groups")
public class ContactGroupRestHateoasController {


    private ContactGroupRepository contactGroupRepository;

    public ContactGroupRestHateoasController(ContactGroupRepository contactGroupRepository) {
        this.contactGroupRepository = contactGroupRepository;
    }



    @PutMapping("/{id}")
    public HttpEntity<EntityModel<ContactGroup>> updateContact(@PathVariable Long id, @RequestBody ContactGroup contactGroup){
        return ResponseEntity.notFound().build();
    }

}
