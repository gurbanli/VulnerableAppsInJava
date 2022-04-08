package com.example.ssrfapp.controller;

import com.example.ssrfapp.dto.LoginRequestDTO;
import com.example.ssrfapp.dto.AuthResponseDTO;
import com.example.ssrfapp.model.User;
import com.example.ssrfapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private AuthResponseDTO authResponseDTO;

    @Autowired
    public AuthController(AuthService authService, AuthResponseDTO authResponseDTO){
        this.authService = authService;
        this.authResponseDTO = authResponseDTO;
    }

    @PostMapping()
    public AuthResponseDTO auth(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession httpSession){
        User user = authService.login(loginRequestDTO);
        if (Objects.isNull(user)){
            authResponseDTO.setMessage("Username or password is incorrect !");
            authResponseDTO.setStatusCode(401);
            return authResponseDTO;
        }
        authResponseDTO.setMessage("Authentication is successful !");
        authResponseDTO.setStatusCode(200);
        httpSession.setAttribute("user", user);
        return authResponseDTO;
    }

    @PostMapping("/user")
    public AuthResponseDTO newUser(@RequestBody User user){
        authService.newUser(user);
        authResponseDTO.setMessage("Registration is successful !");
        authResponseDTO.setStatusCode(200);
        return authResponseDTO;
    }
}
