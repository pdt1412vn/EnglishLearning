package com.example.englishlearning.mapper;

import com.example.englishlearning.dto.request.VocabularyRequest;
import com.example.englishlearning.dto.response.VocabularyResponse;
import com.example.englishlearning.entity.Lesson;
import com.example.englishlearning.entity.Vocabulary;
import org.springframework.stereotype.Component;

@Component
public class VocabularyMapper {

    public VocabularyResponse toResponse(Vocabulary vocabulary){

        if(vocabulary==null){
            return null;
        }

        VocabularyResponse dto=new VocabularyResponse();

        dto.setId(vocabulary.getId());
        dto.setLessonId(vocabulary.getLesson().getId());
        dto.setEnglishWord(vocabulary.getEnglishWord());
        dto.setVietnameseMeaning(vocabulary.getVietnameseMeaning());
        dto.setPronunciation(vocabulary.getPronunciation());
        dto.setExampleSentence(vocabulary.getExampleSentence());
        dto.setImage(vocabulary.getImage());
        dto.setAudio(vocabulary.getAudio());

        return dto;

    }

    public Vocabulary toEntity(VocabularyRequest request){

        if(request==null){
            return null;
        }

        Vocabulary vocabulary=new Vocabulary();

        Lesson lesson=new Lesson();

        lesson.setId(request.getLessonId());

        vocabulary.setLesson(lesson);
        vocabulary.setEnglishWord(request.getEnglishWord());
        vocabulary.setVietnameseMeaning(request.getVietnameseMeaning());
        vocabulary.setPronunciation(request.getPronunciation());
        vocabulary.setExampleSentence(request.getExampleSentence());
        vocabulary.setImage(request.getImage());
        vocabulary.setAudio(request.getAudio());

        return vocabulary;

    }

}