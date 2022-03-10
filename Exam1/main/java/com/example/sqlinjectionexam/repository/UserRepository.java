package com.example.sqlinjectionexam.repository;

import com.example.sqlinjectionexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
