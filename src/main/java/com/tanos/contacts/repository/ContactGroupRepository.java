package com.tanos.contacts.repository;

import com.tanos.contacts.model.ContactGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactGroupRepository extends JpaRepository<ContactGroup, Long> {


}
