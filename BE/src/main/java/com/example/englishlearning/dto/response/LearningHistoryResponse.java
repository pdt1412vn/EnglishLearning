package com.example.englishlearning.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LearningHistoryResponse {

    private Integer id;

    private Integer userId;

    private Integer lessonId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer learningTime;

}