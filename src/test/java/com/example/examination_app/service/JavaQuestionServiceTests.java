package com.example.examination_app.service;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.impl.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaQuestionServiceTests {

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    public void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    public void testAddAndFindAll() {
        javaQuestionService.add(new Question("Что такое ООП?", "Объектно-ориентированное программирование."));
        javaQuestionService.add(new Question("Что такое наследование?", "Механизм повторного использования кода."));

        List<Question> foundQuestions = javaQuestionService.findAll();
        assertThat(foundQuestions).hasSize(2);
    }

    @Test
    public void testRemoveQuestion() {
        javaQuestionService.add(new Question("Что такое ООП?", "Объектно-ориентированное программирование."));
        assertThat(javaQuestionService.findAll()).hasSize(1);

        javaQuestionService.remove("Что такое ООП?", "Объектно-ориентированное программирование.");
        assertThat(javaQuestionService.findAll()).isEmpty();
    }
}