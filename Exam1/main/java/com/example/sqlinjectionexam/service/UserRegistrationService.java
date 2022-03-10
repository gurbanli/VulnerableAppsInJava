package com.example.sqlinjectionexam.service;

import com.example.sqlinjectionexam.model.User;
import com.example.sqlinjectionexam.repository.UserRepository;
import com.example.sqlinjectionexam.util.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private HashUtility hashUtility;
    private UserRepository userRepository;

    @Autowired
    public UserRegistrationService(HashUtility hashUtility, UserRepository userRepository){
        this.hashUtility = hashUtility;
        this.userRepository = userRepository;
    }

    public boolean register(User user){
        try{
            user.setPassword(hashUtility.hashPassword(user.getPassword()));
            userRepository.save(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
