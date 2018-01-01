package com.sougata.bookstore.service;

import com.sougata.bookstore.domain.User;

public interface UserService {

    Iterable<User> list();
    User save(User user);
    User getById(long id);
    void delete(Long id);
    void update(User user);
    User getByEmail(String email);
}
