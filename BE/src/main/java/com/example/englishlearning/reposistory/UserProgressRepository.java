package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProgressRepository extends JpaRepository<UserProgress,Integer>{

    List<UserProgress> findByUserId(Integer userId);

    List<UserProgress> findByLessonId(Integer lessonId);

}