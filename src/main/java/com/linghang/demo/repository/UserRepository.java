package com.linghang.demo.repository;

import com.linghang.demo.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
