package com.tanos.contacts.rest.hateoes.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
@NoArgsConstructor
public class ContactResource extends RepresentationModel<ContactResource> {

    private String name;
    private String phone;
    private String email;
}
