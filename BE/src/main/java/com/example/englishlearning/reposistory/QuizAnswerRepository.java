package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer,Integer>{

    List<QuizAnswer> findByQuizId(Integer quizId);

}