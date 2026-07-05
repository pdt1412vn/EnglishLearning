package com.example.englishlearning;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EnglishLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishLearningApplication.class, args);
    }

}
