package com.example.englishlearning.dto.response;

import lombok.Data;

@Data
public class VocabularyResponse {

    private Integer id;

    private Integer lessonId;

    private String englishWord;

    private String vietnameseMeaning;

    private String pronunciation;

    private String exampleSentence;

    private String image;

    private String audio;

}