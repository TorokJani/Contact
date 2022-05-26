package com.tanos.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id ;

    @NotEmpty
    @Size(min = 3, message = "name must be at least 3 characters long")
    @Column(name = "name", nullable = false)
    private String name;
    private String phone;

    @Email
    private String email;

    @Past
    private Date birthDate;
    private String address;

@PastOrPresent
    private Date createdAt;

@ManyToOne
@JoinColumn(name = "group_id")
    private ContactGroup contactGroup;

}
