package com.example.clientsidevulns;

import com.example.clientsidevulns.utility.HashUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientSideVulnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientSideVulnsApplication.class, args);
    }
    @Bean
    public HashUtility hashUtility(){
        return new HashUtility();
    }
}
