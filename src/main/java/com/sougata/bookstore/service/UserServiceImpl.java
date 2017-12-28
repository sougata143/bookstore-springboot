package com.sougata.bookstore.service;

import com.sougata.bookstore.domain.User;
import com.sougata.bookstore.repository.UserReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserReository userReository;

    @Autowired
    public UserServiceImpl(UserReository userReository) {
        this.userReository = userReository;
    }

    @Override
    public Iterable<User> list() {
        return userReository.findAll();
    }

    @Override
    public User save(User user) {
        return userReository.save(user);
    }

    @Override
    public User getById(long id) {
        return userReository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        userReository.delete(id);
    }

    @Override
    public void update(User user) {
        userReository.save(user);
    }
}
