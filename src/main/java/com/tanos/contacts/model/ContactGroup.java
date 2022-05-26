package com.tanos.contacts.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class ContactGroup extends AuditableEntity<String> {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String name;

    @OneToMany
    private List<Contact> contacts;
}
