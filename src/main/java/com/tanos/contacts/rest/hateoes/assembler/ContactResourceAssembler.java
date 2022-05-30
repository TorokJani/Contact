package com.tanos.contacts.rest.hateoes.assembler;

import com.tanos.contacts.model.Contact;
import com.tanos.contacts.rest.hateoes.ContactRestHateoasController;
import com.tanos.contacts.rest.hateoes.resources.ContactResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class ContactResourceAssembler  extends RepresentationModelAssemblerSupport<Contact, ContactResource> {

    public ContactResourceAssembler() {
        super(ContactRestHateoasController.class, ContactResource.class);
    }

    @Override
    public ContactResource toModel(Contact entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
