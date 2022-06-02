package com.tanos.contacts.model.hateoas.assemblers;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.rest.ContactRestHateoasController;
import com.tanos.contacts.model.hateoas.resources.ContactResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class ContactResourceAssembler  extends RepresentationModelAssemblerSupport<Contact, ContactResource> {


    public ContactResourceAssembler() {
        super(ContactRestHateoasController.class, ContactResource.class);
    }

    @Override
    public ContactResource toModel(Contact entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected ContactResource instantiateModel(Contact entity){
        ContactResource resource = new ContactResource();
        resource.setAddress(entity.getAddress());
        resource.setBirthDate(entity.getBirthDate());
        resource.setCreatedAt(entity.getCreatedAt());
        resource.setEmail(entity.getEmail());
        resource.setGroup(new ContactGroupResourceAssembler().instantiateModel(entity.getGroup()));
        resource.setLastModifiedAt(entity.getLastModifiedAt());
        resource.setLastModifiedBy(entity.getLastModifiedBy());
        resource.setName(entity.getName());
        resource.setPhone(entity.getPhone());
        return resource;
    }
}
