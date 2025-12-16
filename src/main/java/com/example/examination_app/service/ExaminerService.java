package com.example.examination_app.service;

import com.example.examination_app.model.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount) throws Exception;
}