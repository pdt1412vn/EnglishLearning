package com.example.englishlearning.mapper;

import com.example.englishlearning.dto.request.QuizRequest;
import com.example.englishlearning.dto.response.QuizAnswerResponse;
import com.example.englishlearning.dto.response.QuizResponse;
import com.example.englishlearning.entity.Lesson;
import com.example.englishlearning.entity.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizMapper {

    private final QuizAnswerMapper quizAnswerMapper;

    public QuizResponse toResponse(Quiz quiz){

        if(quiz==null){
            return null;
        }

        QuizResponse dto=new QuizResponse();

        dto.setId(quiz.getId());
        dto.setLessonId(quiz.getLesson().getId());
        dto.setQuestion(quiz.getQuestion());
        dto.setQuizType(quiz.getQuizType());
        dto.setExplanation(quiz.getExplanation());

        List<QuizAnswerResponse> answers=
                quiz.getAnswers()
                        .stream()
                        .map(quizAnswerMapper::toResponse)
                        .toList();

        dto.setAnswers(answers);

        return dto;

    }

    public Quiz toEntity(QuizRequest request){

        if(request==null){
            return null;
        }

        Quiz quiz=new Quiz();

        Lesson lesson=new Lesson();
        lesson.setId(request.getLessonId());

        quiz.setLesson(lesson);
        quiz.setQuestion(request.getQuestion());
        quiz.setQuizType(request.getQuizType());
        quiz.setExplanation(request.getExplanation());

        return quiz;

    }

}