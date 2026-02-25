package com.guru.springsecurity_learning.DAO;

import com.guru.springsecurity_learning.Model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByContactId(Long id);
    Page<Contact> findAll(Pageable pageable);
}