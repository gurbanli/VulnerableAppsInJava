package com.example.sqlinjection1.controller;
import com.example.sqlinjection1.model.User;
import com.example.sqlinjection1.util.HashUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    EntityManager entityManager;


    @GetMapping
    public String loginPage(){
        return "index";
    }

    @PostMapping()
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        try{
            HashUtility hashUtility = new HashUtility();
            String hashedPassword = hashUtility.hashPassword(password);
            log.info(hashedPassword);
            User user = (User) entityManager.createNativeQuery("select * from users where username='" + username + "' and password='" + hashedPassword +"';", User.class).getSingleResult();
            session.setAttribute("user", user);
            return "redirect:/products";
        }catch (NoResultException e){
            model.addAttribute("error", "Username or password is incorrect !");
            return "index";
        }
    }
}
