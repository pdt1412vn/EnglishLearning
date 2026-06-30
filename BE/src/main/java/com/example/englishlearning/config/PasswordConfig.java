package com.example.englishlearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Đảm bảo import đúng các class này
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}