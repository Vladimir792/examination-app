package com.example.examination_app.controller;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(JavaQuestionController.class)
public class JavaQuestionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    public void setUp() {
        // Настройка моков перед каждым тестом
    }

    @Test
    public void testAddQuestion() throws Exception {
        Question question = new Question("Что такое ООП?", "Объектно-ориентированное программирование.");

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/java/add")
                        .param("question", question.getQuestion())
                        .param("answer", question.getAnswer())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(javaQuestionService, times(1)).add(question);
    }

    @Test
    public void testRemoveQuestion() throws Exception {
        Question question = new Question("Что такое ООП?", "Объектно-ориентированное программирование.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/java/remove")
                        .param("question", question.getQuestion())
                        .param("answer", question.getAnswer())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(javaQuestionService, times(1)).remove(question.getQuestion(), question.getAnswer());
    }

    @Test
    public void testListQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Что такое ООП?", "Объектно-ориентированное программирование."));
        questions.add(new Question("Что такое наследование?", "Механизм повторного использования кода."));

        when(javaQuestionService.findAll()).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/java"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }
}