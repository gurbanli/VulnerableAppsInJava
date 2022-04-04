package com.example.websecexam2.repository;

import com.example.websecexam2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserByUsername(String username);
}
