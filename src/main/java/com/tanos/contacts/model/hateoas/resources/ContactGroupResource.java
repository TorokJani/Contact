package com.tanos.contacts.model.hateoas.resources;

import com.tanos.contacts.model.Contact;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ContactGroupResource extends RepresentationModel<ContactGroupResource> {
    private String name;
    private List<Contact> contacts;

}
