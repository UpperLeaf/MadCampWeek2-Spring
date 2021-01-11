package com.example.demo.picture;

import com.example.demo.auth.AuthUser;
import com.example.demo.auth.TokenLogin;
import com.example.demo.user.Account;
import com.example.demo.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PictureController {

    private final AccountService accountService;
    private final PictureService pictureService;

    @GetMapping("/picture")
    public ResponseEntity<?> getAllPictures(@TokenLogin AuthUser user) {
        Account account = accountService.findByEmail(user.getEmail());
        List<Picture> pictures = pictureService.getAllPictures(account);
        List<PictureResponseDto> responseDtos = pictures.stream().map(PictureResponseDto::of).collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping("/picture")
    public ResponseEntity<?> uploadPicture(@TokenLogin AuthUser user, @RequestBody PictureRequestDto requestDto){
        Account account = accountService.findByEmail(user.getEmail());
        Picture picture = pictureService.createPicture(account, requestDto);
        return ResponseEntity.ok(PictureResponseDto.of(picture));
    }

    @DeleteMapping("/picture/{id}")
    public ResponseEntity<?> deletePicture(@TokenLogin AuthUser user, @PathVariable Long id){
        Account account = accountService.findByEmail(user.getEmail());
        pictureService.deletePicture(account, id);
        return ResponseEntity.ok().build();
    }
}
