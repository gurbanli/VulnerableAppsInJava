package com.example.sqlinjectionrce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @GetMapping("/")
    public String indexPage(HttpSession session){
        if(session.getAttribute("user") != null) return "redirect:/products";
        return "redirect:/login";
    }

    @RequestMapping(value = "/{file:.+}")
    String jspMapper(@PathVariable String file) {
        return file;
    }

}
