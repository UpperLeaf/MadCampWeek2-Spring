package com.example.demo.token;

import com.example.demo.auth.AuthUser;
import com.example.demo.auth.TokenLogin;
import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final AccountService accountService;

    @GetMapping("/token")
    public ResponseEntity<?> tokenVerify(@TokenLogin AuthUser user) {
        return ResponseEntity.ok().build();
    }
}
