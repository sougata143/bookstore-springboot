package com.sougata.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sougata.bookstore.domain.User;

import java.util.Optional;

public interface UserReository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
