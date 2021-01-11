package com.example.demo.picture;

import lombok.Data;

@Data
public class PictureResponseDto {
    private Long id;
    private String title;
    private String image;


    public static PictureResponseDto of(Picture picture){
        PictureResponseDto pictureResponseDto = new PictureResponseDto();
        pictureResponseDto.id = picture.getId();
        pictureResponseDto.image = picture.getImage();
        pictureResponseDto.title = picture.getTitle();
        return pictureResponseDto;
    }
}
