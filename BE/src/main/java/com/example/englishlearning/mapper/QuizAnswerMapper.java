package com.example.englishlearning.mapper;

import com.example.englishlearning.dto.request.QuizAnswerRequest;
import com.example.englishlearning.dto.response.QuizAnswerResponse;
import com.example.englishlearning.entity.Quiz;
import com.example.englishlearning.entity.QuizAnswer;
import org.springframework.stereotype.Component;

@Component
public class QuizAnswerMapper {

    public QuizAnswerResponse toResponse(QuizAnswer answer){

        if(answer==null){
            return null;
        }

        QuizAnswerResponse dto=new QuizAnswerResponse();

        dto.setId(answer.getId());
        dto.setAnswerContent(answer.getAnswerContent());
        dto.setCorrect(answer.getCorrect());

        return dto;

    }

    public QuizAnswer toEntity(QuizAnswerRequest request){

        if(request==null){
            return null;
        }

        QuizAnswer answer=new QuizAnswer();

        Quiz quiz=new Quiz();
        quiz.setId(request.getQuizId());

        answer.setQuiz(quiz);
        answer.setAnswerContent(request.getAnswerContent());
        answer.setCorrect(request.getCorrect());

        return answer;

    }

}