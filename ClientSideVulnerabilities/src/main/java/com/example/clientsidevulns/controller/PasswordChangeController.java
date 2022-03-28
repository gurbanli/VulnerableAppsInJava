package com.example.clientsidevulns.controller;

import com.example.clientsidevulns.model.User;
import com.example.clientsidevulns.repository.UserRepository;
import com.example.clientsidevulns.utility.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/change")
public class PasswordChangeController {
    private HashUtility hashUtility;
    private UserRepository userRepository;

    @Autowired
    public PasswordChangeController(HashUtility hashUtility, UserRepository userRepository){
        this.hashUtility = hashUtility;
        this.userRepository = userRepository;
    }
    @GetMapping
    public String changePasswordPage(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") != null) {
            return "change";
        }
        else{
            model.addAttribute("error", "You are not logged in !");
            return "index";
        }
    }

    @PostMapping
    public String changePassword(@RequestParam(value="password") String password,
                                 @RequestParam(value="confirm_password") String confirmPassword,
                                 Model model,
                                 HttpSession httpSession)
    {
        if (httpSession.getAttribute("user") != null) {
            User user = (User) httpSession.getAttribute("user");
            if(password.equals(confirmPassword)){
                user.setPassword(hashUtility.hashPassword(password));
                userRepository.save(user);
                return "redirect:/products";
            }
            model.addAttribute("error", "Passwords don't match !");
            return "change";
        }
        else{
            model.addAttribute("error", "You are not logged in !");
            return "index";
        }
    }
}
