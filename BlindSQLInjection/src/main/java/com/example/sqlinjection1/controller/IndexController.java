package com.example.sqlinjection1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String indexPage(HttpSession session){
        if(session.getAttribute("user") != null) return "redirect:/products";
        return "redirect:/login";
    }
}
