package com.example.examination_app.service.impl;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.JavaQuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaQuestionService implements com.example.examination_app.service.JavaQuestionService {
    private final List<Question> questions = new ArrayList<>();

    @Override
    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public boolean remove(String question, String answer) {
        return questions.removeIf(q -> q.getQuestion().equals(question) && q.getAnswer().equals(answer));
    }

    @Override
    public List<Question> findAll() {
        return questions;
    }

    public int getRandomQuestionIndex() {
        Random random = new Random();
        return random.nextInt(questions.size());
    }
}