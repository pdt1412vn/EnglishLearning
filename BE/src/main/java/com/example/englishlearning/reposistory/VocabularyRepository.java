package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabularyRepository extends JpaRepository<Vocabulary,Integer> {

    List<Vocabulary> findByLessonId(Integer lessonId);

}