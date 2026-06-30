package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate,Integer>{

    List<Certificate> findByUserId(Integer userId);

}