package com.example.examination_app.model;

public class Question {
    private String question;
    private String answer;// Конструктор, принимающий вопрос и ответ
    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Геттер для вопроса
    public String getQuestion() {
        return question;
    }

    // Геттер для ответа
    public String getAnswer() {
        return answer;
    }

    //Опционально: equals и hashCode, если планируете сравнивать объекты Question
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (!question.equals(question1.question)) return false;
        return answer.equals(question1.answer);
    }

    @Override
    public int hashCode() {
        int result = question.hashCode();
        result = 31 * result + answer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

}
