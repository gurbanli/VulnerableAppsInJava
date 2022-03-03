package com.example.sqlinjection1.repository;

import com.example.sqlinjection1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
