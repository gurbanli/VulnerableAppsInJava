package com.example.websecexam2.controller;

import com.example.websecexam2.model.User;
import com.example.websecexam2.repository.UserRepository;
import com.example.websecexam2.utility.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private HashUtility hashUtility;

    @Autowired
    public RegistrationController(UserRepository userRepository, HashUtility hashUtility){
        this.userRepository = userRepository;
        this.hashUtility = hashUtility;
    }

    @GetMapping
    public String registrationPage(User user){
        return "register";
    }

    @PostMapping
    public String signUp(User user){
        user.setPassword(hashUtility.hashPassword(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }
}
