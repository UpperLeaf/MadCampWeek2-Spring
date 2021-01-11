package com.example.demo.contact;

import com.example.demo.auth.AuthUser;
import com.example.demo.user.Account;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor @NoArgsConstructor
@Entity @Getter @Setter @EqualsAndHashCode(of = "id")
public class Contact {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    private Account account;
}
