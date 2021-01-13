package com.example.demo.blog;

import com.example.demo.user.Account;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode @AllArgsConstructor
@Entity @Getter @Setter @NoArgsConstructor
public class Blog {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @Lob
    private String bannerImage;

    @Lob
    private String profileImage;

    private String userImageUrl;

    private Boolean hasProfileImage;
    
    @OneToOne
    private Account account;
}
