package com.example.englishlearning.mapper;

import com.example.englishlearning.dto.request.CourseRequest;
import com.example.englishlearning.dto.response.CourseResponse;
import com.example.englishlearning.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponse toResponse(Course course){

        if(course==null){
            return null;
        }

        CourseResponse dto=new CourseResponse();

        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setImage(course.getImage());
        dto.setLevel(course.getLevel());
        dto.setStatus(course.getStatus());

        return dto;

    }

    public Course toEntity(CourseRequest request){

        if(request==null){
            return null;
        }

        Course course=new Course();

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setImage(request.getImage());
        course.setLevel(request.getLevel());
        course.setStatus(request.getStatus());

        return course;

    }

}