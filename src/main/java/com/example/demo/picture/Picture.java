package com.example.demo.picture;

import com.example.demo.user.Account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Picture {

    @Id @GeneratedValue
    private Long id;

    private String url;

    @ManyToOne
    private Account account;
}
