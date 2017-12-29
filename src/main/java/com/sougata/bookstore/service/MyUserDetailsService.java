package com.sougata.bookstore.service;

import com.sougata.bookstore.domain.ApplicationUser;
import com.sougata.bookstore.domain.User;
import com.sougata.bookstore.repository.UserReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserReository userReository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userReository.findByUsername(username);
            if (user == null){
                    throw new UsernameNotFoundException("Username not found "+ username);
            }
            return new ApplicationUser(user);
    }
}
