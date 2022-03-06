package com.example.sqlinjectionrce.repository;

import com.example.sqlinjectionrce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
