package com.example.englishlearning.service.impl;


import com.example.englishlearning.entity.UserProgress;
import com.example.englishlearning.reposistory.UserProgressRepository;
import com.example.englishlearning.service.UserProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProgressServiceImpl implements UserProgressService {

    private final UserProgressRepository userProgressRepository;

    @Override
    public List<UserProgress> getAll() {
        return userProgressRepository.findAll();
    }

    @Override
    public List<UserProgress> getByUser(Integer userId) {
        return userProgressRepository.findByUserId(userId);
    }

    @Override
    public UserProgress save(UserProgress progress) {
        return userProgressRepository.save(progress);
    }

    @Override
    public UserProgress update(Integer id, UserProgress progress) {

        UserProgress old = userProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        old.setUser(progress.getUser());
        old.setLesson(progress.getLesson());
        old.setScore(progress.getScore());
        old.setCompleted(progress.getCompleted());
        old.setCompletedAt(progress.getCompletedAt());

        return userProgressRepository.save(old);
    }

    @Override
    public void delete(Integer id) {
        userProgressRepository.deleteById(id);
    }
}

