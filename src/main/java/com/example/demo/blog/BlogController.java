package com.example.demo.blog;

import com.example.demo.auth.AuthUser;
import com.example.demo.auth.TokenLogin;
import com.example.demo.user.Account;
import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final AccountService accountService;
    private final BlogService blogService;

    @GetMapping("/blog")
    public ResponseEntity<?> getBlog(@TokenLogin AuthUser user) {
        Account account = accountService.findByEmail(user.getEmail());
        BlogResponseDto responseDto = blogService.findOrSaveBlog(account);
        return ResponseEntity.ok(responseDto);
    }


    @GetMapping("/blog/other")
    public ResponseEntity<?> getBlogById(@TokenLogin AuthUser user, String email){
        Account account = accountService.findByEmail(email);
        BlogResponseDto responseDto = blogService.getBlogByEmail(account);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@TokenLogin AuthUser user, @RequestBody PostDto requestDto){
        Account account = accountService.findByEmail(user.getEmail());
        return ResponseEntity.ok(blogService.createPost(account, requestDto));
    }
}
