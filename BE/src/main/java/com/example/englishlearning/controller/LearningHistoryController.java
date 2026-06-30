package com.example.englishlearning.controller;

import com.example.englishlearning.entity.LearningHistory;
import com.example.englishlearning.service.LearningHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LearningHistoryController {

    private final LearningHistoryService learningHistoryService;

    @GetMapping
    public List<LearningHistory> getAll() {
        return learningHistoryService.getAll();
    }

    @PostMapping
    public LearningHistory create(@RequestBody LearningHistory history) {
        return learningHistoryService.save(history);
    }

}
