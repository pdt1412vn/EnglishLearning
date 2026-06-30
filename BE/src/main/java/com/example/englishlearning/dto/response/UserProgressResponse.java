package com.example.englishlearning.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProgressResponse {

    private Integer id;

    private Integer userId;

    private Integer lessonId;

    private Double score;

    private Boolean completed;

    private LocalDateTime completedAt;

}