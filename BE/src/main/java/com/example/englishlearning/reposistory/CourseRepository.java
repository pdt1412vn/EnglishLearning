package com.example.englishlearning.reposistory;

import com.example.englishlearning.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
