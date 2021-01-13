package com.example.demo.blog;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(of = "id") @NoArgsConstructor
@Entity @Getter @Setter @AllArgsConstructor
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Column(length = 500)
    private String content;

    @ManyToOne
    private Blog blog;
}
