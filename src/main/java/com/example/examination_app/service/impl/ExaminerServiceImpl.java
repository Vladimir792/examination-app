package com.example.examination_app.service.impl;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.ExaminerService;
import com.example.examination_app.service.JavaQuestionService;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class ExaminerServiceImpl implements ExaminerService {
    private final Map<String, JavaQuestionService<?>> servicesBySubject = new HashMap<>();

    public ExaminerServiceImpl() {
        servicesBySubject.put("java", new JavaQuestionService());
    }

    @Override
    public List<Question> getQuestions(int amount) throws Exception {
        JavaQuestionService<?> javaService = servicesBySubject.get("java");
        List<Question> allQuestions = javaService.findAll();

        if (allQuestions.isEmpty() || amount > allQuestions.size())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough questions available.");

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