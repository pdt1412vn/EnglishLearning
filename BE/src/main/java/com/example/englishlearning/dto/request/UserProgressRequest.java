package com.example.englishlearning.dto.request;

import lombok.Data;

@Data
public class UserProgressRequest {

    private Integer userId;

    private Integer lessonId;

    private Double score;

    private Boolean completed;

}