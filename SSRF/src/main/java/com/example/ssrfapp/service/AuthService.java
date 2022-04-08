package com.example.ssrfapp.service;

import com.example.ssrfapp.dto.LoginRequestDTO;
import com.example.ssrfapp.model.User;
import com.example.ssrfapp.repository.UserRepository;
import com.example.ssrfapp.utility.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private HashUtility hashUtility;

    @Autowired
    public AuthService(UserRepository userRepository, HashUtility hashUtility){
        this.userRepository = userRepository;
        this.hashUtility = hashUtility;
    }

    public User login(LoginRequestDTO loginRequestDTO){
        return userRepository.findUserByUsernameAndPassword(loginRequestDTO.getUsername(), hashUtility.hashPassword(loginRequestDTO.getPassword()));
    }

    public void newUser(User user){
        user.setPassword(hashUtility.hashPassword(user.getPassword()));
        userRepository.save(user);
    }
}
