package com.tanos.contacts.model.hateoas.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class ContactResource extends RepresentationModel<ContactResource> {

    private String name;
    private String phone;
    private String email;
    private String address;
    private Date birthDate;
    private ContactGroupResource group;
    private Date createdAt;
    private Date lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;
}
