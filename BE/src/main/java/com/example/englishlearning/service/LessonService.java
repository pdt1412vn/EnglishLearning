package com.example.englishlearning.service;

import com.example.englishlearning.dto.request.LessonRequest;
import com.example.englishlearning.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {

    List<LessonResponse> getAll();

    LessonResponse getById(Integer id);

    List<LessonResponse> getByCourse(Integer courseId);

    LessonResponse save(LessonRequest request);

    LessonResponse update(Integer id, LessonRequest request);

    void delete(Integer id);

}