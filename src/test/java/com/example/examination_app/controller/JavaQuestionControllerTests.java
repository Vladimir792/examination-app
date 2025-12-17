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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JavaQuestionController.class)
public class JavaQuestionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaQuestionService javaQuestionService;

    private Question question1;
    private Question question2;

    @BeforeEach
    public void setUp() {
        question1 = new Question("Что такое ООП?", "Объектно-ориентированное программирование.");
        question2 = new Question("Что такое наследование?", "Механизм повторного использования кода.");
    }

    @Test
    public void testAddQuestion() throws Exception {

        // Corrected: используем doReturn, чтобы мокировать void метод
        doReturn(question1).when(javaQuestionService).add(any(Question.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/exam/java/add")
                        .param("question", "Что такое ООП?")
                        .param("answer", "Объектно-ориентированное программирование.")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))//Указываем, как передаём данные
                .andExpect(status().isCreated()) //Проверяем статус ответа
                .andExpect(jsonPath("$.question").value("Что такое ООП?")) //Проверяем тело ответа, поле question
                .andExpect(jsonPath("$.answer").value("Объектно-ориентированное программирование.")); //Проверяем тело ответа, поле answer

        verify(javaQuestionService, times(1)).add(any(Question.class)); //Проверяем, что метод add был вызван
    }

    @Test
    public void testRemoveQuestion() throws Exception {
        // Corrected: используем doNothing, чтобы мокировать void метод
        doNothing().when(javaQuestionService).remove(anyString(), anyString());

        mockMvc.perform(MockMvcRequestBuilders.delete("/exam/java/remove")
                        .param("question", "Что такое ООП?")
                        .param("answer", "Объектно-ориентированное программирование."))
                .andExpect(status().isNoContent());  //Ожидаем 204 No Content

        verify(javaQuestionService, times(1)).remove("Что такое ООП?", "Объектно-ориентированное программирование."); //Проверяем вызов метода remove
    }

    @Test
    public void testListQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        when(javaQuestionService.findAll()).thenReturn(questions); //Мокируем вызов метода findAll

        mockMvc.perform(MockMvcRequestBuilders.get("/exam/java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) //Проверяем размер списка
                .andExpect(jsonPath("$[0].question").value("Что такое ООП?")) //Проверяем первый элемент списка
                .andExpect(jsonPath("$[0].answer").value("Объектно-ориентированное программирование.")) //Проверяем первый элемент списка, поле answer
                .andExpect(jsonPath("$[1].question").value("Что такое наследование?")) //Проверяем второй элемент списка
                .andExpect(jsonPath("$[1].answer").value("Механизм повторного использования кода."));  //Проверяем второй элемент списка, поле answer

        verify(javaQuestionService, times(1)).findAll(); //Проверяем вызов метода findAll
    }
}
