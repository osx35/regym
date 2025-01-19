package com.example.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.webapi", "com.example.data"})
public class RegymWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegymWebApiApplication.class, args);
    }
}
