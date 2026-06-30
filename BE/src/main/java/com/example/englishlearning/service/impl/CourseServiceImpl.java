package com.example.englishlearning.service.impl;

import com.example.englishlearning.dto.request.CourseRequest;
import com.example.englishlearning.dto.response.CourseResponse;
import com.example.englishlearning.entity.Course;
import com.example.englishlearning.mapper.CourseMapper;
import com.example.englishlearning.reposistory.CourseRepository;
import com.example.englishlearning.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    @Override
    public List<CourseResponse> getAll() {

        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toResponse)
                .toList();

    }

    @Override
    public CourseResponse getById(Integer id) {

        return courseMapper.toResponse(
                courseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Course not found"))
        );

    }

    @Override
    public CourseResponse save(CourseRequest request) {

        Course course=courseMapper.toEntity(request);

        return courseMapper.toResponse(
                courseRepository.save(course)
        );

    }

    @Override
    public CourseResponse update(Integer id, CourseRequest request) {

        Course course=courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setImage(request.getImage());
        course.setLevel(request.getLevel());
        course.setStatus(request.getStatus());

        return courseMapper.toResponse(
                courseRepository.save(course)
        );

    }

    @Override
    public void delete(Integer id) {

        courseRepository.deleteById(id);

    }

}