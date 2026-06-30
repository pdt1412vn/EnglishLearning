package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByCourseId(Integer courseId);

}