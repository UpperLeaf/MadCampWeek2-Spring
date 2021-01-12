package com.example.demo.blog;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BlogResponseDto {

    private String title;
    private String description;
    private String bannerImage;
    private String userImageUrl;

    private List<PostDto> post;

    public static BlogResponseDto of(Blog blog, List<Post> posts) {
        BlogResponseDto responseDto = new BlogResponseDto();
        responseDto.title = blog.getTitle();
        responseDto.description = blog.getDescription();
        responseDto.bannerImage = blog.getBannerImage();
        responseDto.userImageUrl = blog.getUserImageUrl();
        responseDto.post = posts.stream().map(PostDto::of).collect(Collectors.toList());
        return responseDto;
    }
}
