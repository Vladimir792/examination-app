package com.example.examination_app.service;

import com.example.examination_app.model.Question;

import java.util.List;

public interface QuestionService<T extends Question> {
    void add(T question);
    boolean remove(String question, String answer);
    List<T> findAll();
}