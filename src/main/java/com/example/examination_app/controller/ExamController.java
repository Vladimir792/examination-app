package com.example.examination_app.controller;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    @Autowired
    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable int amount) {
        try {
            return ResponseEntity.ok(examinerService.getQuestions(amount));
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}