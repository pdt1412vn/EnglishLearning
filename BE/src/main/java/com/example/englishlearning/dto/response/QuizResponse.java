package com.example.englishlearning.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuizResponse {

    private Integer id;

    private Integer lessonId;

    private String question;

    private String quizType;

    private String explanation;

    private List<QuizAnswerResponse> answers;

}