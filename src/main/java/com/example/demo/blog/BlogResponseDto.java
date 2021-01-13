package com.example.demo.blog;

import com.example.demo.contact.Contact;
import com.example.demo.contact.ContactResponseDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BlogResponseDto {

    private String title;
    private String description;
    private String bannerImage;
    private String userImageUrl;

    private String profileImage;
    private Boolean hasProfileImage = false;

    private List<PostDto> post;
    private List<ContactResponseDto> contacts;

    public static BlogResponseDto of(Blog blog, List<Post> posts) {
        BlogResponseDto responseDto = new BlogResponseDto();
        responseDto.title = blog.getTitle();
        responseDto.description = blog.getDescription();
        responseDto.bannerImage = blog.getBannerImage();
        responseDto.userImageUrl = blog.getUserImageUrl();
        responseDto.post = posts.stream().map(PostDto::of).collect(Collectors.toList());
        responseDto.hasProfileImage = blog.getHasProfileImage();
        responseDto.profileImage = blog.getProfileImage();
        return responseDto;
    }

    public static BlogResponseDto of(Blog blog, List<Post> posts, List<Contact> contacts) {
        BlogResponseDto responseDto = BlogResponseDto.of(blog, posts);
        responseDto.contacts = contacts.stream().map(ContactResponseDto::of).collect(Collectors.toList());
        return responseDto;
    }
}
