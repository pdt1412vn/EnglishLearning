package com.example.englishlearning.service.impl;


import com.example.englishlearning.entity.LearningHistory;
import com.example.englishlearning.reposistory.LearningHistoryRepository;
import com.example.englishlearning.service.LearningHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningHistoryServiceImpl implements LearningHistoryService {

    private final LearningHistoryRepository learningHistoryRepository;

    @Override
    public List<LearningHistory> getAll() {
        return learningHistoryRepository.findAll();
    }

    @Override
    public LearningHistory save(LearningHistory history) {
        return learningHistoryRepository.save(history);
    }
}

