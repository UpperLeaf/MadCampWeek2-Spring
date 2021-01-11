package com.example.demo.contact;

import com.example.demo.auth.TokenLogin;
import com.example.demo.auth.AuthUser;
import com.example.demo.user.Account;
import com.example.demo.user.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ContactController {

    private final AccountService accountService;
    private final ContactService contactService;

    @GetMapping("/contact")
    public ResponseEntity<?> retrieveContact(@TokenLogin AuthUser user) {
        Account account = accountService.findByEmail(user.getEmail());
        return ResponseEntity.ok(contactService.getAllContacts(account));
    }

    @PostMapping("/contact")
    public ResponseEntity<?> createContact(@TokenLogin AuthUser user, ContactResponseDto responseDto) {
        Account account = accountService.findByEmail(user.getEmail());
        Contact contact = contactService.createContact(account, responseDto);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<?> deleteContact(@TokenLogin AuthUser user, @PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
