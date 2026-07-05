package com.example.englishlearning.service;

import com.example.englishlearning.Security_JWT.LoginResponse;
import com.example.englishlearning.Security_JWT.RegisterRequest;
import com.example.englishlearning.dto.request.LoginRequest;
import com.example.englishlearning.entity.User;


public interface AuthService {

    LoginResponse login(LoginRequest request);

    User register(RegisterRequest request);

}