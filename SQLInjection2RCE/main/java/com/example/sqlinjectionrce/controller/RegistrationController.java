package com.example.sqlinjectionrce.controller;

import com.example.sqlinjectionrce.repository.*;
import com.example.sqlinjectionrce.model.User;
import com.example.sqlinjectionrce.util.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
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
