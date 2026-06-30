package com.example.englishlearning.controller;

import com.example.englishlearning.Security_JWT.ApiResponse;
import com.example.englishlearning.dto.request.CourseRequest;
import com.example.englishlearning.dto.response.CourseResponse;
import com.example.englishlearning.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ApiResponse<List<CourseResponse>> getAll(){

        return new ApiResponse<>(
                true,
                "Lấy danh sách khóa học thành công",
                courseService.getAll()
        );

    }

    @GetMapping("/{id}")
    public ApiResponse<CourseResponse> getById(@PathVariable Integer id){

        return new ApiResponse<>(
                true,
                "Success",
                courseService.getById(id)
        );

    }


    @PostMapping
    public ApiResponse<CourseResponse> save(@RequestBody CourseRequest request){

        return new ApiResponse<>(
                true,
                "Thêm khóa học thành công",
                courseService.save(request)
        );

    }

    @PutMapping("/{id}")
    public ApiResponse<CourseResponse> update(
            @PathVariable Integer id,
            @RequestBody CourseRequest request){

        return new ApiResponse<>(
                true,
                "Cập nhật khóa học thành công",
                courseService.update(id,request)
        );

    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id){

        courseService.delete(id);

        return new ApiResponse<>(
                true,
                "Xóa khóa học thành công",
                null
        );

    }

}