package com.example.englishlearning.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CertificateResponse {

    private Integer id;

    private Integer userId;

    private Integer courseId;

    private LocalDateTime issueDate;

    private String certificateCode;

}