package com.tanos.contacts.rest;

import com.tanos.contacts.model.ContactGroup;
import com.tanos.contacts.model.hateoas.assemblers.ContactGroupResourceAssembler;
import com.tanos.contacts.model.hateoas.resources.ContactGroupResource;
import com.tanos.contacts.repository.ContactGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*")
@Slf4j
public class ContactGroupRestController {

    private ContactGroupRepository contactGroupRepository;

    @Autowired
    public ContactGroupRestController(ContactGroupRepository contactGroupRepository) {
        this.contactGroupRepository = contactGroupRepository;
    }

    @GetMapping
    public CollectionModel<ContactGroupResource> groups() {
        List<ContactGroup> groups = contactGroupRepository.findAll();

        CollectionModel<ContactGroupResource> contactGroupResources = new ContactGroupResourceAssembler().toCollectionModel(groups);
        contactGroupResources.add(linkTo(methodOn(ContactGroupRestController.class).groups()).withSelfRel());

        return contactGroupResources;
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<EntityModel<ContactGroupResource>> getGroupById(@PathVariable Long id) {
        try {
            ContactGroup group = contactGroupRepository.findById(id).orElseThrow();
            EntityModel<ContactGroupResource> groupResource = EntityModel.of(new ContactGroupResourceAssembler().toModel(group));
            return ResponseEntity.ok(groupResource);
        } catch (NoSuchElementException e) {
            log.error("Group with id was not found: " + id, e);
            return ResponseEntity.notFound().build();

        }


    }


}

