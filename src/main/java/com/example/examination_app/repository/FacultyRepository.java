package com.example.examination_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.examination_app.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}