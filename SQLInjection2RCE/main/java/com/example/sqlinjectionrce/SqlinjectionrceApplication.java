package com.example.sqlinjectionrce;

import com.example.sqlinjectionrce.util.HashUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SqlinjectionrceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlinjectionrceApplication.class, args);
    }
    @Bean
    public HashUtility hashUtility(){
        return new HashUtility();
    }
}
