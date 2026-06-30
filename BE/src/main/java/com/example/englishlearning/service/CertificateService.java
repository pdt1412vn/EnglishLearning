package com.example.englishlearning.service;

import com.example.englishlearning.entity.Certificate;

import java.util.List;

public interface CertificateService {

    List<Certificate> getAll();

    Certificate save(Certificate certificate);

}