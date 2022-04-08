package com.example.ssrfapp.controller;

import com.example.ssrfapp.service.SSRFService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private SSRFService ssrfService;

    public AdminController(SSRFService ssrfService){
        this.ssrfService = ssrfService;
    }

    @GetMapping
    public String adminPage(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(!ssrfService.getClientIp(servletRequestAttributes.getRequest()).equals("127.0.0.1")){
            return "forbidden !";
        }
        return "secret";
    }
}
