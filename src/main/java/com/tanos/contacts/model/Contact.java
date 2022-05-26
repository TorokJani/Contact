package com.tanos.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Contact")
public class Contact extends AuditableEntity<String> {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
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

@ManyToOne
@JoinColumn(name = "group_id")
    private ContactGroup group;



}
