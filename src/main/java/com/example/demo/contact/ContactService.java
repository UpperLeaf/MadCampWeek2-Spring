package com.example.demo.contact;

import com.example.demo.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> getAllContacts(Account account){
        return contactRepository.findAllByAccount(account);
    }

    public Contact createContact(Account account, ContactResponseDto responseDto) {
        Contact contact = new Contact();
        contact.setAccount(account);
        contact.setEmail(responseDto.getEmail());
        contact.setName(responseDto.getName());
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
