package com.example.clientsidevulns.service;

import com.example.clientsidevulns.model.User;
import com.example.clientsidevulns.repository.UserRepository;
import com.example.clientsidevulns.utility.HashUtility;
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
