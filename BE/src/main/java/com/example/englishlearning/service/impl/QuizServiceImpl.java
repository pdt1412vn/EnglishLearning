package com.example.englishlearning.service.impl;

import com.example.englishlearning.dto.request.QuizRequest;
import com.example.englishlearning.dto.response.QuizResponse;
import com.example.englishlearning.entity.Lesson;
import com.example.englishlearning.entity.Quiz;
import com.example.englishlearning.mapper.QuizMapper;
import com.example.englishlearning.reposistory.LessonRepository;
import com.example.englishlearning.reposistory.QuizRepository;
import com.example.englishlearning.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    private final LessonRepository lessonRepository;

    private final QuizMapper quizMapper;

    @Override
    public List<QuizResponse> getAll() {

        return quizRepository.findAll()
                .stream()
                .map(quizMapper::toResponse)
                .toList();

    }

    @Override
    public QuizResponse getById(Integer id) {

        return quizMapper.toResponse(
                quizRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Quiz not found"))
        );

    }

    @Override
    public List<QuizResponse> getByLesson(Integer lessonId) {

        return quizRepository.findByLessonId(lessonId)
                .stream()
                .map(quizMapper::toResponse)
                .toList();

    }

    @Override
    public QuizResponse save(QuizRequest request) {

        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Quiz quiz = quizMapper.toEntity(request);

        quiz.setLesson(lesson);

        return quizMapper.toResponse(
                quizRepository.save(quiz)
        );

    }

    @Override
    public QuizResponse update(Integer id, QuizRequest request) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        quiz.setLesson(lesson);
        quiz.setQuestion(request.getQuestion());
        quiz.setQuizType(request.getQuizType());
        quiz.setExplanation(request.getExplanation());

        return quizMapper.toResponse(
                quizRepository.save(quiz)
        );

    }

    @Override
    public void delete(Integer id) {

        quizRepository.deleteById(id);

    }

}