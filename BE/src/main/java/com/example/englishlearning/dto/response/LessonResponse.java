package com.example.englishlearning.dto.response;

import lombok.Data;

@Data
public class LessonResponse {

    private Integer id;

    private Integer courseId;

    private String title;

    private String description;

    private String videoUrl;

    private Integer lessonOrder;

    private Integer duration;

    private Integer status;

}