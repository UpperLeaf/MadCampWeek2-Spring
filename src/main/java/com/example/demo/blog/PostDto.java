package com.example.demo.blog;

import lombok.Data;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String content;

    public static PostDto of(Post post){
        PostDto responseDto = new PostDto();
        responseDto.content = post.getContent();
        responseDto.title = post.getTitle();
        responseDto.id = post.getId();
        return  responseDto;
    }
}
