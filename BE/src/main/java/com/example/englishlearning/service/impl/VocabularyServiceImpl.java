package com.example.englishlearning.service.impl;

import com.example.englishlearning.dto.request.VocabularyRequest;
import com.example.englishlearning.dto.response.VocabularyResponse;
import com.example.englishlearning.entity.Lesson;
import com.example.englishlearning.entity.Vocabulary;
import com.example.englishlearning.mapper.VocabularyMapper;
import com.example.englishlearning.reposistory.LessonRepository;
import com.example.englishlearning.reposistory.VocabularyRepository;
import com.example.englishlearning.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    private final LessonRepository lessonRepository;

    private final VocabularyMapper vocabularyMapper;

    @Override
    public List<VocabularyResponse> getAll() {

        return vocabularyRepository.findAll()
                .stream()
                .map(vocabularyMapper::toResponse)
                .toList();

    }

    @Override
    public VocabularyResponse getById(Integer id) {

        return vocabularyMapper.toResponse(
                vocabularyRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Vocabulary not found"))
        );

    }

    @Override
    public List<VocabularyResponse> getByLesson(Integer lessonId) {

        return vocabularyRepository.findByLessonId(lessonId)
                .stream()
                .map(vocabularyMapper::toResponse)
                .toList();

    }

    @Override
    public VocabularyResponse save(VocabularyRequest request) {

        Lesson lesson=lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Vocabulary vocabulary=vocabularyMapper.toEntity(request);

        vocabulary.setLesson(lesson);

        return vocabularyMapper.toResponse(
                vocabularyRepository.save(vocabulary)
        );

    }

    @Override
    public VocabularyResponse update(Integer id, VocabularyRequest request) {

        Vocabulary vocabulary=vocabularyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vocabulary not found"));

        Lesson lesson=lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        vocabulary.setLesson(lesson);
        vocabulary.setEnglishWord(request.getEnglishWord());
        vocabulary.setVietnameseMeaning(request.getVietnameseMeaning());
        vocabulary.setPronunciation(request.getPronunciation());
        vocabulary.setExampleSentence(request.getExampleSentence());
        vocabulary.setImage(request.getImage());
        vocabulary.setAudio(request.getAudio());

        return vocabularyMapper.toResponse(
                vocabularyRepository.save(vocabulary)
        );

    }

    @Override
    public void delete(Integer id) {

        vocabularyRepository.deleteById(id);

    }

}