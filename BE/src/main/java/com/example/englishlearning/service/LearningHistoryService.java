package com.example.englishlearning.service;

import com.example.englishlearning.entity.LearningHistory;

import java.util.List;

public interface LearningHistoryService {

    List<LearningHistory> getAll();

    LearningHistory save(LearningHistory history);

}
