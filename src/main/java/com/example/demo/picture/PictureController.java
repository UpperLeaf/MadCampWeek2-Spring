package com.example.demo.picture;

import com.example.demo.auth.AuthUser;
import com.example.demo.auth.TokenLogin;
import com.example.demo.user.Account;
import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PictureController {

    private final AccountService accountService;
    private final PictureService pictureService;

    @GetMapping("/picture")
    public List<Picture> getAllPictures(@TokenLogin AuthUser user) {
        Account account = accountService.findByEmail(user.getEmail());
        return pictureService.getAllPictures(account);
    }

    @PostMapping("/picture")
    public Picture uploadPicture(@TokenLogin AuthUser user, PictureRequestDto requestDto){
        Account account = accountService.findByEmail(user.getEmail());

        return null;
    }
}
