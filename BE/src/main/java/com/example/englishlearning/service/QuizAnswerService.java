package com.example.englishlearning.service;

import com.example.englishlearning.dto.request.QuizAnswerRequest;
import com.example.englishlearning.dto.response.QuizAnswerResponse;

import java.util.List;

public interface QuizAnswerService {

    List<QuizAnswerResponse> getAll();

    QuizAnswerResponse getById(Integer id);

    List<QuizAnswerResponse> getByQuiz(Integer quizId);

    QuizAnswerResponse save(QuizAnswerRequest request);

    QuizAnswerResponse update(Integer id, QuizAnswerRequest request);

    void delete(Integer id);

}