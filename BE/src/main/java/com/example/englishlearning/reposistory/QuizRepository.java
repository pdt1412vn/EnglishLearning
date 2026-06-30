package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Integer>{

    List<Quiz> findByLessonId(Integer lessonId);

}