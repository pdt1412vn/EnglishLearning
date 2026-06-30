package com.example.englishlearning.service;

import com.example.englishlearning.dto.request.CourseRequest;
import com.example.englishlearning.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAll();

    CourseResponse getById(Integer id);

    CourseResponse save(CourseRequest request);

    CourseResponse update(Integer id, CourseRequest request);

    void delete(Integer id);

}