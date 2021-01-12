package com.example.demo.blog;

import com.example.demo.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findBlogByAccount(Account account);
}
