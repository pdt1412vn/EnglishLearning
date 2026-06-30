package com.example.englishlearning.dto.request;

import lombok.Data;

@Data
public class LessonRequest {

    private Integer courseId;

    private String title;

    private String description;

    private String videoUrl;

    private Integer lessonOrder;

    private Integer duration;

    private Integer status;

}
