package com.tanos.contacts.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class ContactGroup {
    @Id
    private UUID Id;
    private String name;

    @OneToMany
    private List<Contact> contacts;
}
