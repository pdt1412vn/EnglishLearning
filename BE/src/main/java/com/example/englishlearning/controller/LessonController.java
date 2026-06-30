package com.example.englishlearning.controller;

import com.example.englishlearning.Security_JWT.ApiResponse;
import com.example.englishlearning.dto.request.LessonRequest;
import com.example.englishlearning.dto.response.LessonResponse;
import com.example.englishlearning.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ApiResponse<List<LessonResponse>> getAll() {

        return new ApiResponse<>(
                true,
                "Lấy danh sách bài học thành công",
                lessonService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<LessonResponse> getById(@PathVariable Integer id) {

        return new ApiResponse<>(
                true,
                "Success",
                lessonService.getById(id)
        );
    }

    @GetMapping("/course/{courseId}")
    public ApiResponse<List<LessonResponse>> getByCourse(@PathVariable Integer courseId) {

        return new ApiResponse<>(
                true,
                "Lấy danh sách bài học theo khóa học thành công",
                lessonService.getByCourse(courseId)
        );
    }

    @PostMapping
    public ApiResponse<LessonResponse> save(@RequestBody LessonRequest request) {

        return new ApiResponse<>(
                true,
                "Thêm bài học thành công",
                lessonService.save(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<LessonResponse> update(
            @PathVariable Integer id,
            @RequestBody LessonRequest request) {

        return new ApiResponse<>(
                true,
                "Cập nhật bài học thành công",
                lessonService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {

        lessonService.delete(id);

        return new ApiResponse<>(
                true,
                "Xóa bài học thành công",
                null
        );
    }
}