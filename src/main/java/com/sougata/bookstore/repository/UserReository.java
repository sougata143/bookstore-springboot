package com.sougata.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sougata.bookstore.domain.User;

public interface UserReository extends JpaRepository<User, Long> {
}
