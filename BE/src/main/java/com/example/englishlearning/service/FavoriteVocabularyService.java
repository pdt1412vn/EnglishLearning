package com.example.englishlearning.service;

import com.example.englishlearning.entity.FavoriteVocabulary;

import java.util.List;

public interface FavoriteVocabularyService {

    List<FavoriteVocabulary> getAll();

    List<FavoriteVocabulary> getByUser(Integer userId);

    FavoriteVocabulary save(FavoriteVocabulary favorite);

    void delete(Integer id);

}
