package com.example.demo.picture;

import com.example.demo.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findAllByAccount(Account account);
}
