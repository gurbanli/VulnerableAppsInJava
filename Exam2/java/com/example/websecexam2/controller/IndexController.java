package com.example.websecexam2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "redirect:/login";
    }

    @GetMapping(value = "/{file:shell.+}")
    public String serveFile(@PathVariable String file) {
        System.out.println("girdi");
        return file;
    }
}