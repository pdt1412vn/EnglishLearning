package com.example.englishlearning.controller;

import com.example.englishlearning.Security_JWT.ApiResponse;
import com.example.englishlearning.dto.request.QuizAnswerRequest;
import com.example.englishlearning.dto.response.QuizAnswerResponse;
import com.example.englishlearning.service.QuizAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-answers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizAnswerController {

    private final QuizAnswerService quizAnswerService;

    @GetMapping
    public ApiResponse<List<QuizAnswerResponse>> getAll(){

        return new ApiResponse<>(
                true,
                "Lấy danh sách đáp án thành công",
                quizAnswerService.getAll()
        );

    }

    @GetMapping("/{id}")
    public ApiResponse<QuizAnswerResponse> getById(
            @PathVariable Integer id){

        return new ApiResponse<>(
                true,
                "Success",
                quizAnswerService.getById(id)
        );

    }

    @GetMapping("/quiz/{quizId}")
    public ApiResponse<List<QuizAnswerResponse>> getByQuiz(
            @PathVariable Integer quizId){

        return new ApiResponse<>(
                true,
                "Lấy danh sách đáp án thành công",
                quizAnswerService.getByQuiz(quizId)
        );

    }

    @PostMapping
    public ApiResponse<QuizAnswerResponse> save(
            @RequestBody QuizAnswerRequest request){

        return new ApiResponse<>(
                true,
                "Thêm đáp án thành công",
                quizAnswerService.save(request)
        );

    }

    @PutMapping("/{id}")
    public ApiResponse<QuizAnswerResponse> update(
            @PathVariable Integer id,
            @RequestBody QuizAnswerRequest request){

        return new ApiResponse<>(
                true,
                "Cập nhật đáp án thành công",
                quizAnswerService.update(id,request)
        );

    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(
            @PathVariable Integer id){

        quizAnswerService.delete(id);

        return new ApiResponse<>(
                true,
                "Xóa đáp án thành công",
                null
        );

    }

}