package com.example.englishlearning.dto.request;

import lombok.Data;

@Data
public class FavoriteVocabularyRequest {

    private Integer userId;

    private Integer vocabularyId;

}