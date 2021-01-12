package com.example.demo.blog;

import com.example.demo.auth.AuthUser;
import com.example.demo.auth.TokenLogin;
import com.example.demo.user.Account;
import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final AccountService accountService;
    private final BlogService blogService;

    @GetMapping("/blog")
    public ResponseEntity<?> getBlog(@TokenLogin AuthUser user) {
        Account account = accountService.findByEmail(user.getEmail());
        return ResponseEntity.ok(blogService.findOrSaveBlog(account));
    }


    @GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlogById(@TokenLogin AuthUser user, @PathVariable Long id){
        BlogResponseDto responseDto = blogService.getBlogById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@TokenLogin AuthUser user, PostDto requestDto){
        Account account = accountService.findByEmail(user.getEmail());
        return ResponseEntity.ok(blogService.createPost(account, requestDto));
    }
}
