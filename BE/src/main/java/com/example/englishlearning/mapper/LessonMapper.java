package com.example.englishlearning.mapper;

import com.example.englishlearning.dto.request.LessonRequest;
import com.example.englishlearning.dto.response.LessonResponse;
import com.example.englishlearning.entity.Course;
import com.example.englishlearning.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public LessonResponse toResponse(Lesson lesson){

        if(lesson == null){
            return null;
        }

        LessonResponse dto = new LessonResponse();

        dto.setId(lesson.getId());
        dto.setCourseId(lesson.getCourse().getId());
        dto.setTitle(lesson.getTitle());
        dto.setDescription(lesson.getDescription());
        dto.setVideoUrl(lesson.getVideoUrl());
        dto.setLessonOrder(lesson.getLessonOrder());
        dto.setDuration(lesson.getDuration());
        dto.setStatus(lesson.getStatus());

        return dto;
    }

    public Lesson toEntity(LessonRequest request){

        if(request == null){
            return null;
        }

        Lesson lesson = new Lesson();

        Course course = new Course();
        course.setId(request.getCourseId());

        lesson.setCourse(course);
        lesson.setTitle(request.getTitle());
        lesson.setDescription(request.getDescription());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setLessonOrder(request.getLessonOrder());
        lesson.setDuration(request.getDuration());
        lesson.setStatus(request.getStatus());

        return lesson;
    }

}