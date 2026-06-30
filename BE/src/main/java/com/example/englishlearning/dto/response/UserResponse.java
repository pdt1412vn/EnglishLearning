package com.example.englishlearning.dto.response;


import lombok.Data;

@Data
public class UserResponse {

    private Integer id;

    private String fullName;

    private String email;

    private String avatar;

    private String role;

    private Integer status;

}

