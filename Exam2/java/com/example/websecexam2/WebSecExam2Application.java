package com.example.websecexam2;

import com.example.websecexam2.utility.HashUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebSecExam2Application {

    public static void main(String[] args) {
        SpringApplication.run(WebSecExam2Application.class, args);
    }
    @Bean
    public HashUtility hashUtility(){
        return new HashUtility();
    }
}
