package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
