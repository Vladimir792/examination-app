package com.example.examination_app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;

    // Пустой конструктор нужен для JPA
    public Student() {}

    // Конструктор для создания объекта из Postman
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    // Геттеры и сеттеры (в IDEA можно нажать Alt+Insert -> Getter and Setter)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}