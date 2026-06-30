package com.example.englishlearning.controller;

import com.example.englishlearning.Security_JWT.ApiResponse;
import com.example.englishlearning.dto.request.VocabularyRequest;
import com.example.englishlearning.dto.response.VocabularyResponse;
import com.example.englishlearning.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vocabularies")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @GetMapping
    public ApiResponse<List<VocabularyResponse>> getAll(){

        return new ApiResponse<>(
                true,
                "Lấy danh sách từ vựng thành công",
                vocabularyService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<VocabularyResponse> getById(@PathVariable Integer id){

        return new ApiResponse<>(
                true,
                "Success",
                vocabularyService.getById(id)
        );
    }

    @GetMapping("/lesson/{lessonId}")
    public ApiResponse<List<VocabularyResponse>> getByLesson(@PathVariable Integer lessonId){

        return new ApiResponse<>(
                true,
                "Lấy danh sách từ vựng thành công",
                vocabularyService.getByLesson(lessonId)
        );
    }

    @PostMapping
    public ApiResponse<VocabularyResponse> save(@RequestBody VocabularyRequest request){

        return new ApiResponse<>(
                true,
                "Thêm từ vựng thành công",
                vocabularyService.save(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<VocabularyResponse> update(
            @PathVariable Integer id,
            @RequestBody VocabularyRequest request){

        return new ApiResponse<>(
                true,
                "Cập nhật từ vựng thành công",
                vocabularyService.update(id,request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id){

        vocabularyService.delete(id);

        return new ApiResponse<>(
                true,
                "Xóa từ vựng thành công",
                null
        );
    }

}