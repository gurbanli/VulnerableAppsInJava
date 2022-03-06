package com.example.sqlinjectionrce.controller;
import com.example.sqlinjectionrce.model.User;
import com.example.sqlinjectionrce.util.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    EntityManager entityManager;

    @Autowired
    HashUtility hashUtility;


    @GetMapping
    public String loginPage(){
        return "index";
    }

    @PostMapping()
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        try{
            Query query = entityManager.createNativeQuery("select * from users where username= ? and password = ?", User.class);
            query.setParameter(1, username);
            query.setParameter(2, hashUtility.hashPassword(password));
            User user = (User) query.getSingleResult();

            session.setAttribute("user", user);
            return "redirect:/products";
        }catch (NoResultException e){
            model.addAttribute("error", "Username or password is incorrect !");
            return "index";
        }
    }
}
