package com.example.ssrfapp;

import com.example.ssrfapp.utility.HashUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SsrfAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsrfAppApplication.class, args);
    }
    @Bean
    public HashUtility hashUtility(){
        return new HashUtility();
    }
}
