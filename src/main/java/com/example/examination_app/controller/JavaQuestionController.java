package com.example.examination_app.controller;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.JavaQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    @Autowired
    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addQuestion(
            @RequestParam String question,
            @RequestParam String answer
    ) {
        javaQuestionService.add(new Question(question, answer));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> deleteQuestion(
            @RequestParam String question,
            @RequestParam String answer
    ) {
        if (!javaQuestionService.remove(question, answer)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Question>> listQuestions() {
        return ResponseEntity.ok(javaQuestionService.findAll());
    }
}