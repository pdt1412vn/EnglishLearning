package com.example.englishlearning.service.impl;


import com.example.englishlearning.entity.FavoriteVocabulary;
import com.example.englishlearning.reposistory.FavoriteVocabularyRepository;
import com.example.englishlearning.service.FavoriteVocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteVocabularyServiceImpl implements FavoriteVocabularyService {

    private final FavoriteVocabularyRepository favoriteVocabularyRepository;

    @Override
    public List<FavoriteVocabulary> getAll() {
        return favoriteVocabularyRepository.findAll();
    }

    @Override
    public List<FavoriteVocabulary> getByUser(Integer userId) {
        return favoriteVocabularyRepository.findByUserId(userId);
    }

    @Override
    public FavoriteVocabulary save(FavoriteVocabulary favorite) {
        return favoriteVocabularyRepository.save(favorite);
    }

    @Override
    public void delete(Integer id) {
        favoriteVocabularyRepository.deleteById(id);
    }
}

