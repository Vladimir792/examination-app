package com.example.examination_app.service;

import com.example.examination_app.model.Student;
import com.example.examination_app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Получение всех студентов
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    // Поиск одного студента по ID
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Создание или обновление студента
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // Удаление студента
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}