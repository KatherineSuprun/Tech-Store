package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class TechStoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(TechStoreApplication.class, args);
    }
}
