package com.example.englishlearning.dto.request;

import lombok.Data;

@Data
public class QuizAnswerRequest {

    private Integer quizId;

    private String answerContent;

    private Boolean correct;

}
