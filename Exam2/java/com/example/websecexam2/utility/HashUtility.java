package com.example.websecexam2.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtility {
    public String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte [] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<bytes.length;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException e){
            return "";
        }

    }
}
