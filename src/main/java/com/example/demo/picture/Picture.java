package com.example.demo.picture;

import com.example.demo.user.Account;
import lombok.*;

import javax.persistence.*;

@Entity @Builder @AllArgsConstructor
@Setter @Getter @NoArgsConstructor
public class Picture {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String image;

    @ManyToOne
    private Account account;
}
