package com.example.sqlinjection1.controller;

import com.example.sqlinjection1.repository.*;
import com.example.sqlinjection1.model.User;
import com.example.sqlinjection1.util.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String registrationPage(User user){
        return "register";
    }

    @PostMapping
    public String signUp(User user){
        HashUtility hashUtility = new HashUtility();
        user.setPassword(hashUtility.hashPassword(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }
}
