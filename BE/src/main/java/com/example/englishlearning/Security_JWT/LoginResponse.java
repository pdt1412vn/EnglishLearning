package com.example.englishlearning.Security_JWT;


import com.example.englishlearning.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;

    private UserResponse user;

}
