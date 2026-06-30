package com.example.englishlearning.dto.response;

import lombok.Data;

@Data
public class QuizAnswerResponse {

    private Integer id;

    private String answerContent;

    private Boolean correct;

}