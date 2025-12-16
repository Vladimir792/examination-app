package com.example.examination_app.controller;

import com.example.examination_app.model.Question;
import com.example.examination_app.service.ExaminerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@WebMvcTest(ExamController.class)
public class ExamControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExaminerService examinerService;

    @BeforeEach
    public void setUp() {
        // Настройка моков перед каждым тестом
    }

    @Test
    public void testGetQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Вопрос 1", "Ответ 1"));
        questions.add(new Question("Вопрос 2", "Ответ 2"));

        when(examinerService.getQuestions(2)).thenReturn(questions);

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/get/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetQuestionsNotEnough() throws Exception {
        when(examinerService.getQuestions(3)).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough questions available."));

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/get/3"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}