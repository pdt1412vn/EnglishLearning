package com.example.englishlearning.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LearningHistoryRequest {

    private Integer userId;

    private Integer lessonId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer learningTime;

}