package com.example.demo.contact;

import lombok.Data;

@Data
public class ContactResponseDto {

    private Long id;
    private String name;
    private String email;


    public static ContactResponseDto of(Contact contact){
        ContactResponseDto contactResponseDto = new ContactResponseDto();
        contactResponseDto.id = contact.getId();
        contactResponseDto.name = contact.getName();
        contactResponseDto.email = contact.getEmail();
        return contactResponseDto;
    }
}
