package com.example.sqlinjection1;

import com.example.sqlinjection1.util.HashUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sqlinjection1Application {
    public static void main(String[] args) {
        SpringApplication.run(Sqlinjection1Application.class, args);
    }

    @Bean
    public HashUtility hashUtility(){
        return new HashUtility();
    }
}
