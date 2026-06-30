package com.example.englishlearning.service.impl;


import com.example.englishlearning.Security_JWT.JwtUtil;
import com.example.englishlearning.Security_JWT.LoginResponse;

import com.example.englishlearning.dto.request.LoginRequest;
import com.example.englishlearning.entity.User;
import com.example.englishlearning.mapper.UserMapper;
import com.example.englishlearning.reposistory.UserRepository;
import com.example.englishlearning.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest request){

        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){

            throw new RuntimeException("Sai mật khẩu");

        }

        String token=jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(

                token,

                userMapper.toResponse(user)

        );

    }

}