package com.example.englishlearning.dto.request;

import lombok.Data;

@Data
public class VocabularyRequest {

    private Integer lessonId;

    private String englishWord;

    private String vietnameseMeaning;

    private String pronunciation;

    private String exampleSentence;

    private String image;

    private String audio;

}