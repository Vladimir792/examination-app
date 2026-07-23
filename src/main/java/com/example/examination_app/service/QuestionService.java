package com.example.examination_app.service;

import com.example.examination_app.model.Question;

import java.util.List;

public interface QuestionService {
    void add(Question question);
    boolean remove(String question, String answer);
    List<Question> findAll();
}