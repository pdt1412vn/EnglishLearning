package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.FavoriteVocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteVocabularyRepository extends JpaRepository<FavoriteVocabulary,Integer>{

    List<FavoriteVocabulary> findByUserId(Integer userId);

}