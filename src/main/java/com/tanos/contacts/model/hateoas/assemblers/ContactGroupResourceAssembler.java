package com.tanos.contacts.model.hateoas.assemblers;

import com.tanos.contacts.model.ContactGroup;
import com.tanos.contacts.model.hateoas.resources.ContactGroupResource;
import com.tanos.contacts.rest.ContactGroupRestHateoasController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class ContactGroupResourceAssembler extends RepresentationModelAssemblerSupport<ContactGroup, ContactGroupResource> {

    public ContactGroupResourceAssembler() {
        super(ContactGroupRestHateoasController.class, ContactGroupResource.class);
    }

    @Override
    public ContactGroupResource toModel(ContactGroup entity) {
        return createModelWithId(entity.getId(), entity);
    }

    @Override
    protected ContactGroupResource instantiateModel(ContactGroup entity){
        ContactGroupResource resource = new ContactGroupResource();
        resource.setName(entity.getName());
        resource.setContacts(entity.getContacts());
        return resource;
    }
}
