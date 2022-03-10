package com.example.sqlinjectionexam.controller;

import com.example.sqlinjectionexam.model.User;
import com.example.sqlinjectionexam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }


    @GetMapping("/login")
    public String loginPage(HttpSession httpSession){
        if (httpSession.getAttribute("user") != null) return "redirect:/products";
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession httpSession, @RequestParam("username") String username, @RequestParam("password") String password){
        if(username.contains(" ")) {
            model.addAttribute("error", "You want to hack us? :)");
            return "index";
        }
        User user = loginService.Login(username, password);
        if(user != null){
            httpSession.setAttribute("user", user);
            return "redirect:/products";
        }else{
            model.addAttribute("error", "Username or password is incorrect !");
            return "index";
        }
    }
}
