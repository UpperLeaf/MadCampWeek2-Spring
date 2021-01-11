package com.example.demo.contact;

import com.example.demo.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByAccount(Account account);
}
