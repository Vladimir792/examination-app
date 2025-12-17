package com.example.examination_app.service.impl;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.ExaminerService;
import com.example.examination_app.service.QuestionService;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) throws Exception {
        List<Question> allQuestions = questionService.findAll();

        if (allQuestions.isEmpty() || amount > allQuestions.size())
            throw new Exception("Not enough questions available.");

        Set<Integer> usedIndexes = new HashSet<>();
        List<Question> result = new ArrayList<>(amount);

        Random random = new Random();
        while (usedIndexes.size() < amount) {
            int index = random.nextInt(allQuestions.size());
            if (!usedIndexes.contains(index)) {
                usedIndexes.add(index);
                result.add(allQuestions.get(index));
            }
        }

        return result;
    }
}