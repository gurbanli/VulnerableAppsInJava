package com.example.sqlinjectionexam;

import com.example.sqlinjectionexam.util.HashUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SqlinjectionexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlinjectionexamApplication.class, args);
    }
    @Bean
    public HashUtility hashUtility(){
        return new HashUtility();
    }
}
