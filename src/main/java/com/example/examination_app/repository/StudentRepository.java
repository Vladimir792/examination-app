package com.example.examination_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.examination_app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}