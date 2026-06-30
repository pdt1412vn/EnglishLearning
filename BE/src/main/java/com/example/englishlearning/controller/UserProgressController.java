package com.example.englishlearning.controller;

import com.example.englishlearning.entity.UserProgress;
import com.example.englishlearning.service.UserProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserProgressController {

    private final UserProgressService userProgressService;

    @GetMapping
    public List<UserProgress> getAll() {
        return userProgressService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<UserProgress> getByUser(@PathVariable Integer userId) {
        return userProgressService.getByUser(userId);
    }

    @PostMapping
    public UserProgress create(@RequestBody UserProgress progress) {
        return userProgressService.save(progress);
    }

    @PutMapping("/{id}")
    public UserProgress update(@PathVariable Integer id,
                               @RequestBody UserProgress progress) {
        return userProgressService.update(id, progress);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userProgressService.delete(id);
    }

}

