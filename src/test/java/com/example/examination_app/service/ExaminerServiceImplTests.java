package com.example.examination_app.service;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExaminerServiceImplTests {

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setUp() {
        examinerService = new ExaminerServiceImpl();
        examinerService.questionService.add(new Question("Вопрос 1", "Ответ 1"));
        examinerService.questionService.add(new Question("Вопрос 2", "Ответ 2"));
    }

    @Test
    public void testGetUniqueQuestions() {
        List<Question> questions = examinerService.getQuestions(2);
        assertThat(questions).hasSize(2);
    }

    @Test
    public void testNotEnoughQuestions() {
        Throwable exception = null;
        try {
            examinerService.getQuestions(3);
        } catch (Throwable t) {
            exception = t;
        }
        assertThat(exception).isInstanceOf(Exception.class);
    }
}