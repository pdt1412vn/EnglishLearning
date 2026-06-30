package com.example.englishlearning.service.impl;

import com.example.englishlearning.dto.request.LessonRequest;
import com.example.englishlearning.dto.response.LessonResponse;
import com.example.englishlearning.entity.Course;
import com.example.englishlearning.entity.Lesson;
import com.example.englishlearning.mapper.LessonMapper;
import com.example.englishlearning.reposistory.CourseRepository;
import com.example.englishlearning.reposistory.LessonRepository;
import com.example.englishlearning.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final LessonMapper lessonMapper;

    @Override
    public List<LessonResponse> getAll() {

        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toResponse)
                .toList();
    }

    @Override
    public LessonResponse getById(Integer id) {

        return lessonMapper.toResponse(
                lessonRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Lesson not found"))
        );
    }

    @Override
    public List<LessonResponse> getByCourse(Integer courseId) {

        return lessonRepository.findByCourseId(courseId)
                .stream()
                .map(lessonMapper::toResponse)
                .toList();
    }

    @Override
    public LessonResponse save(LessonRequest request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Lesson lesson = lessonMapper.toEntity(request);
        lesson.setCourse(course);

        return lessonMapper.toResponse(
                lessonRepository.save(lesson)
        );
    }

    @Override
    public LessonResponse update(Integer id, LessonRequest request) {

        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        lesson.setCourse(course);
        lesson.setTitle(request.getTitle());
        lesson.setDescription(request.getDescription());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setLessonOrder(request.getLessonOrder());
        lesson.setDuration(request.getDuration());
        lesson.setStatus(request.getStatus());

        return lessonMapper.toResponse(
                lessonRepository.save(lesson)
        );
    }

    @Override
    public void delete(Integer id) {
        lessonRepository.deleteById(id);
    }
}