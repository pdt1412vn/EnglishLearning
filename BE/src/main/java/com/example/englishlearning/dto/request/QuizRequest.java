package com.example.englishlearning.dto.request;

import lombok.Data;

@Data
public class QuizRequest {

    private Integer lessonId;

    private String question;

    private String quizType;

    private String explanation;

}