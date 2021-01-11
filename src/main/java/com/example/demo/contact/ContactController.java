package com.example.demo.contact;

import com.example.demo.auth.TokenLogin;
import com.example.demo.auth.AuthUser;
import com.example.demo.exception.UnAuthorizedException;
import com.example.demo.user.Account;
import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ContactController {

    private final AccountService accountService;
    private final ContactService contactService;

    @GetMapping("/contact")
    public ResponseEntity<?> retrieveContact(@TokenLogin AuthUser user) {
        Account account = accountService.findByEmail(user.getEmail());
        List<Contact> contactList = contactService.getAllContacts(account);
        return ResponseEntity.ok(contactList.stream().map(ContactResponseDto::of).collect(Collectors.toList()));
    }

    @PostMapping("/contact")
    public ResponseEntity<?> createContact(@TokenLogin AuthUser user, @RequestBody ContactResponseDto responseDto) {
        Account account = accountService.findByEmail(user.getEmail());
        Contact contact = contactService.createContact(account, responseDto);
        return ResponseEntity.ok(ContactResponseDto.of(contact));
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<?> updateContact(@TokenLogin AuthUser user, @PathVariable Long id, @RequestBody ContactResponseDto responseDto) {
        Account account = accountService.findByEmail(user.getEmail());
        Contact contact = contactService.findById(id);
        if(contact.getAccount() != account)
            throw new UnAuthorizedException();

        contactService.updateContact(contact, responseDto);
        return ResponseEntity.ok(ContactResponseDto.of(contact));
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<?> deleteContact(@TokenLogin AuthUser user, @PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
