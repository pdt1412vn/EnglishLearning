package com.example.englishlearning.dto.response;

import lombok.Data;

@Data
public class CourseResponse {

    private Integer id;

    private String title;

    private String description;

    private String image;

    private String level;

    private Integer status;

}