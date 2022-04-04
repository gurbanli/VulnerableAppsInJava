package com.example.websecexam2.service;

import com.example.websecexam2.model.User;
import com.example.websecexam2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
public class ForgotPasswordService {
    private SendEmailService sendEmailService;
    private UserRepository userRepository;

    @Autowired
    public ForgotPasswordService(SendEmailService sendEmailService, UserRepository userRepository){
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
    }

    public void newToken(String username) throws Exception {
        try{
            User user = userRepository.findUserByUsername(username);
            String secret = user.getUsername() + "_" + user.getProducts().get(user.getProducts().size()-1).getProductName();
            String encoded_secret = Base64.getEncoder().encodeToString(secret.getBytes());
            sendEmailService.send(encoded_secret);
        }catch (Exception e){
            throw new Exception();
        }
    }
    public User isValidToken(String username, String token){
        try{
            User user = userRepository.findUserByUsername(username);
            String secret = new String(Base64.getDecoder().decode(token));
            if(secret.split("_")[0].equals(username) && secret.split("_")[1].equals(user.getProducts().get(user.getProducts().size() - 1).getProductName()))
                return user;
            else return null;
        }catch (Exception e){
            return null;
        }
    }
}
