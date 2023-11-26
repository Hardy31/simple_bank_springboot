package com.bankdone.simple_bank_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleBankSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBankSpringbootApplication.class, args);
    }

}
