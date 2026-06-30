package com.example.englishlearning.service;

import com.example.englishlearning.Security_JWT.LoginResponse;
import com.example.englishlearning.dto.request.LoginRequest;


public interface AuthService {

    LoginResponse login(LoginRequest request);

}