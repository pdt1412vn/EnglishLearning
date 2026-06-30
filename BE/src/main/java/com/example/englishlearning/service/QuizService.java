package com.example.englishlearning.service;

import com.example.englishlearning.dto.request.QuizRequest;
import com.example.englishlearning.dto.response.QuizResponse;

import java.util.List;

public interface QuizService {

    List<QuizResponse> getAll();

    QuizResponse getById(Integer id);

    List<QuizResponse> getByLesson(Integer lessonId);

    QuizResponse save(QuizRequest request);

    QuizResponse update(Integer id, QuizRequest request);

    void delete(Integer id);

}