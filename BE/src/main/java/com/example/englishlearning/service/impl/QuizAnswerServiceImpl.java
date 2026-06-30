package com.example.englishlearning.service.impl;

import com.example.englishlearning.dto.request.QuizAnswerRequest;
import com.example.englishlearning.dto.response.QuizAnswerResponse;
import com.example.englishlearning.entity.Quiz;
import com.example.englishlearning.entity.QuizAnswer;
import com.example.englishlearning.mapper.QuizAnswerMapper;
import com.example.englishlearning.reposistory.QuizAnswerRepository;
import com.example.englishlearning.reposistory.QuizRepository;
import com.example.englishlearning.service.QuizAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizAnswerServiceImpl implements QuizAnswerService {

    private final QuizAnswerRepository quizAnswerRepository;

    private final QuizRepository quizRepository;

    private final QuizAnswerMapper quizAnswerMapper;

    @Override
    public List<QuizAnswerResponse> getAll() {

        return quizAnswerRepository.findAll()
                .stream()
                .map(quizAnswerMapper::toResponse)
                .toList();

    }

    @Override
    public QuizAnswerResponse getById(Integer id) {

        return quizAnswerMapper.toResponse(
                quizAnswerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Answer not found"))
        );

    }

    @Override
    public List<QuizAnswerResponse> getByQuiz(Integer quizId) {

        return quizAnswerRepository.findByQuizId(quizId)
                .stream()
                .map(quizAnswerMapper::toResponse)
                .toList();

    }

    @Override
    public QuizAnswerResponse save(QuizAnswerRequest request) {

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        QuizAnswer answer = quizAnswerMapper.toEntity(request);

        answer.setQuiz(quiz);

        return quizAnswerMapper.toResponse(
                quizAnswerRepository.save(answer)
        );

    }

    @Override
    public QuizAnswerResponse update(Integer id, QuizAnswerRequest request) {

        QuizAnswer answer = quizAnswerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        answer.setQuiz(quiz);
        answer.setAnswerContent(request.getAnswerContent());
        answer.setCorrect(request.getCorrect());

        return quizAnswerMapper.toResponse(
                quizAnswerRepository.save(answer)
        );

    }

    @Override
    public void delete(Integer id) {

        quizAnswerRepository.deleteById(id);

    }

}