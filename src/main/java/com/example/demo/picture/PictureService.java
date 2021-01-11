package com.example.demo.picture;

import com.example.demo.exception.UnAuthorizedException;
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

    public Picture createPicture(Account account, PictureRequestDto requestDto) {
        Picture picture = new Picture();
        picture.setAccount(account);
        picture.setImage(requestDto.getImage());
        picture.setTitle(requestDto.getTitle());
        return pictureRepository.save(picture);
    }

    public void deletePicture(Account account, Long id) {
        Picture picture = pictureRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if(picture.getAccount() != account)
            throw new UnAuthorizedException();

        pictureRepository.delete(picture);
    }
}
