package com.example.englishlearning.controller;

import com.example.englishlearning.entity.Certificate;
import com.example.englishlearning.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public List<Certificate> getAll() {
        return certificateService.getAll();
    }

    @PostMapping
    public Certificate create(@RequestBody Certificate certificate) {
        return certificateService.save(certificate);
    }

}

