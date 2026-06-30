package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.LearningHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningHistoryRepository extends JpaRepository<LearningHistory,Integer>{

    List<LearningHistory> findByUserId(Integer userId);

}