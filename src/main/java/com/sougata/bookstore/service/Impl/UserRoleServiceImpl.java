package com.sougata.bookstore.service.Impl;

import com.sougata.bookstore.domain.Userrole;
import com.sougata.bookstore.repository.UserRoleRepository;
import com.sougata.bookstore.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public List<Userrole> list() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<Userrole> list(long id) {
        return (List<Userrole>) userRoleRepository.findByUserid(id);
    }
}
