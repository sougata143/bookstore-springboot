package com.sougata.bookstore.controller;

import com.sougata.bookstore.domain.User;
import com.sougata.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Iterable<User> listuser(){
        return userService.list();
    }

    @PostMapping("/save")
    public User saveUser(User user){
        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") long id){
        userService.delete(id);
    }

}
