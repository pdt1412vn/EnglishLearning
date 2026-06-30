package com.example.englishlearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vocabularies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "english_word")
    private String englishWord;

    @Column(name = "vietnamese_meaning")
    private String vietnameseMeaning;

    private String pronunciation;

    @Column(name = "example_sentence")
    private String exampleSentence;

    private String image;

    private String audio;

}
