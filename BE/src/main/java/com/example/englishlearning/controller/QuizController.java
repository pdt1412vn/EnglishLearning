package com.example.englishlearning.controller;

import com.example.englishlearning.Security_JWT.ApiResponse;
import com.example.englishlearning.dto.request.QuizRequest;
import com.example.englishlearning.dto.response.QuizResponse;
import com.example.englishlearning.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ApiResponse<List<QuizResponse>> getAll(){

        return new ApiResponse<>(
                true,
                "Lấy danh sách câu hỏi thành công",
                quizService.getAll()
        );

    }

    @GetMapping("/{id}")
    public ApiResponse<QuizResponse> getById(
            @PathVariable Integer id){

        return new ApiResponse<>(
                true,
                "Success",
                quizService.getById(id)
        );

    }

    @GetMapping("/lesson/{lessonId}")
    public ApiResponse<List<QuizResponse>> getByLesson(
            @PathVariable Integer lessonId){

        return new ApiResponse<>(
                true,
                "Lấy danh sách câu hỏi thành công",
                quizService.getByLesson(lessonId)
        );

    }

    @PostMapping
    public ApiResponse<QuizResponse> save(
            @RequestBody QuizRequest request){

        return new ApiResponse<>(
                true,
                "Thêm câu hỏi thành công",
                quizService.save(request)
        );

    }

    @PutMapping("/{id}")
    public ApiResponse<QuizResponse> update(
            @PathVariable Integer id,
            @RequestBody QuizRequest request){

        return new ApiResponse<>(
                true,
                "Cập nhật thành công",
                quizService.update(id,request)
        );

    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(
            @PathVariable Integer id){

        quizService.delete(id);

        return new ApiResponse<>(
                true,
                "Xóa thành công",
                null
        );

    }

}