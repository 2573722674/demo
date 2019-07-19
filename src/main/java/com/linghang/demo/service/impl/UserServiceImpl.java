package com.linghang.demo.service.impl;

import com.linghang.demo.data.User;
import com.linghang.demo.repository.UserRepository;
import com.linghang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        Assert.notNull(repository, "repository must not be null");
        this.repository = repository;
    }

    @Override
    public User findByName(String name) {
        return repository.findById(name).orElse(null);
    }

    @Override
    public User register(User user) {
        return repository.save(user);
    }
}
