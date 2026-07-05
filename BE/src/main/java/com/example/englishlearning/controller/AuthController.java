package com.example.englishlearning.controller;

import com.example.englishlearning.Security_JWT.ApiResponse;
import com.example.englishlearning.Security_JWT.LoginResponse;
import com.example.englishlearning.Security_JWT.RegisterRequest;
import com.example.englishlearning.dto.request.LoginRequest;
import com.example.englishlearning.entity.User;
import com.example.englishlearning.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest request){

        return new ApiResponse<>(

                true,

                "Đăng nhập thành công",

                authService.login(request)

        );

    }

    @PostMapping("/register")
    public ApiResponse<User> register(
            @RequestBody RegisterRequest request){

        return new ApiResponse<>(

                true,

                "Đăng ký thành công",

                authService.register(request)

        );

    }

}

