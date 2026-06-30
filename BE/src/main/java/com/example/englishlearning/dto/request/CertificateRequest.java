package com.example.englishlearning.dto.request;

import lombok.Data;

@Data
public class CertificateRequest {

    private Integer userId;

    private Integer courseId;

    private String certificateCode;

}