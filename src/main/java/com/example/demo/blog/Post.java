package com.example.demo.blog;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(of = "id") @NoArgsConstructor
@Entity @Getter @Setter @AllArgsConstructor
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    private Blog blog;
}
