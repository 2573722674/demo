package com.linghang.demo.service.impl;

import com.linghang.demo.data.User;
import com.linghang.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByName() {

    }

    @Test
    public void register() {
        User user = new User("yzy", "yuan", "http://xxx.png");
        userService.register(user);
    }
}