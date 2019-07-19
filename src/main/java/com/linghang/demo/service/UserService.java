package com.linghang.demo.service;

import com.linghang.demo.data.User;

public interface UserService {

    User findByName(String name);

    User register(User user);

}
