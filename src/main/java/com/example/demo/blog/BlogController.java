package com.example.demo.blog;

import com.example.demo.auth.AuthUser;
import com.example.demo.auth.TokenLogin;
import com.example.demo.contact.Contact;
import com.example.demo.contact.ContactResponseDto;
import com.example.demo.contact.ContactService;
import com.example.demo.user.Account;
import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final AccountService accountService;
    private final ContactService contactService;
    private final BlogService blogService;

    @GetMapping("/blog")
    public ResponseEntity<?> getBlog(@TokenLogin AuthUser user) {
        Account account = accountService.findByEmail(user.getEmail());
        BlogResponseDto responseDto = blogService.findOrSaveBlog(account);
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping("/blog")
    public ResponseEntity<?> updateBlog(@TokenLogin AuthUser user, @RequestBody BlogRequestDto requestDto){
        Account account = accountService.findByEmail(user.getEmail());
        blogService.updateBlog(account, requestDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/blog/other")
    public ResponseEntity<?> getBlogById(@TokenLogin AuthUser user, String email){
        Account account = accountService.findByEmail(email);
        BlogResponseDto responseDto = blogService.getBlogByEmail(account);
        List<Contact> contacts = contactService.getAllContacts(account);
        responseDto.setContacts(contacts.stream().map(ContactResponseDto::of).collect(Collectors.toList()));
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping("/post")
    public ResponseEntity<?> createPost(@TokenLogin AuthUser user, @RequestBody PostDto requestDto){
        Account account = accountService.findByEmail(user.getEmail());
        return ResponseEntity.ok(blogService.createPost(account, requestDto));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updatePost(@TokenLogin AuthUser user, @RequestBody PostDto requestDto, @PathVariable Long id){
        Account account = accountService.findByEmail(user.getEmail());
        blogService.updatePost(account, requestDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@TokenLogin AuthUser user, @PathVariable Long id){
        Account account = accountService.findByEmail(user.getEmail());
        blogService.deletePost(account, id);
        return ResponseEntity.ok().build();
    }

}
