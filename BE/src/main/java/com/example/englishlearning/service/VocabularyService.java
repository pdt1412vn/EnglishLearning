package com.example.englishlearning.service;

import com.example.englishlearning.dto.request.VocabularyRequest;
import com.example.englishlearning.dto.response.VocabularyResponse;

import java.util.List;

public interface VocabularyService {

    List<VocabularyResponse> getAll();

    VocabularyResponse getById(Integer id);

    List<VocabularyResponse> getByLesson(Integer lessonId);

    VocabularyResponse save(VocabularyRequest request);

    VocabularyResponse update(Integer id, VocabularyRequest request);

    void delete(Integer id);

}