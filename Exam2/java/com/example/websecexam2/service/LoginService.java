package com.example.websecexam2.service;

import com.example.websecexam2.model.User;
import com.example.websecexam2.repository.UserRepository;
import com.example.websecexam2.utility.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private HashUtility hashUtility;
    private UserRepository userRepository;

    @Autowired
    public LoginService(HashUtility hashUtility, UserRepository userRepository){
        this.hashUtility = hashUtility;
        this.userRepository = userRepository;
    }

    public User Login(String username, String password){
        return userRepository.findUserByUsernameAndPassword(username, hashUtility.hashPassword(password));
    }
}
