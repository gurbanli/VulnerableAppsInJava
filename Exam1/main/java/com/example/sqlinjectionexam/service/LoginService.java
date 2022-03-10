package com.example.sqlinjectionexam.service;

import com.example.sqlinjectionexam.model.User;
import com.example.sqlinjectionexam.util.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Service
public class LoginService {
    private HashUtility hashUtility;
    private EntityManager entityManager;

    @Autowired
    public LoginService(HashUtility hashUtility, EntityManager entityManager){
        this.hashUtility = hashUtility;
        this.entityManager = entityManager;
    }

    public User Login(String username, String password){
        try{
            return (User) entityManager.createNativeQuery("select * from users where username='" + username + "' and password='" + hashUtility.hashPassword(password) +"';", User.class).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
