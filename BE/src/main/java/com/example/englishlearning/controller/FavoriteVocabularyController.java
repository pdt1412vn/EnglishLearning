package com.example.englishlearning.controller;

import com.example.englishlearning.entity.FavoriteVocabulary;
import com.example.englishlearning.service.FavoriteVocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FavoriteVocabularyController {

    private final FavoriteVocabularyService favoriteVocabularyService;

    @GetMapping
    public List<FavoriteVocabulary> getAll() {
        return favoriteVocabularyService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<FavoriteVocabulary> getByUser(@PathVariable Integer userId) {
        return favoriteVocabularyService.getByUser(userId);
    }

    @PostMapping
    public FavoriteVocabulary create(@RequestBody FavoriteVocabulary favorite) {
        return favoriteVocabularyService.save(favorite);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        favoriteVocabularyService.delete(id);
    }

}

