package com.example.websecexam2.controller;

import com.example.websecexam2.model.User;
import com.example.websecexam2.repository.UserRepository;
import com.example.websecexam2.service.ForgotPasswordService;
import com.example.websecexam2.utility.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/forgot")
public class ForgotPasswordController {
    private ForgotPasswordService forgotPasswordService;
    private HashUtility hashUtility;
    private UserRepository userRepository;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService, HashUtility hashUtility, UserRepository userRepository){
        this.forgotPasswordService = forgotPasswordService;
        this.hashUtility = hashUtility;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String forgot(Model model){
        return "forgot";
    }
    @PostMapping
    public String forgotPost(RedirectAttributes redirectAttributes, @RequestParam String username){
        try{
            forgotPasswordService.newToken(username);
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/forgot/validate";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "User not found !");
            return "redirect:/forgot";
        }
    }
    @GetMapping("/validate")
    public String validationPage(){
        return "validation";
    }

    @PostMapping("/validate")
    public String tokenValidate(RedirectAttributes redirectAttributes,@RequestParam String username, @RequestParam String token, @RequestParam String password){
        User user = forgotPasswordService.isValidToken(username, token);
        if(Objects.isNull(user)){
            redirectAttributes.addFlashAttribute("error", "Token is invalid !");
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/forgot/validate";
        }
        user.setPassword(hashUtility.hashPassword(password));
        userRepository.save(user);
        return "redirect:/login";
    }
}
