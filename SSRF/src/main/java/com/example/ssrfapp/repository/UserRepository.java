package com.example.ssrfapp.repository;

import com.example.ssrfapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);
}
