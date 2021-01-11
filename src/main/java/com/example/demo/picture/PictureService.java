package com.example.demo.picture;

import com.example.demo.user.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public List<Picture> getAllPictures(Account account) {
        return pictureRepository.findAllByAccount(account);
    }
}
