package com.example.englishlearning.service;

import com.example.englishlearning.entity.UserProgress;

import java.util.List;

public interface UserProgressService {

    List<UserProgress> getAll();

    List<UserProgress> getByUser(Integer userId);

    UserProgress save(UserProgress progress);

    UserProgress update(Integer id, UserProgress progress);

    void delete(Integer id);

}
