package com.example.demo.domain;

public class student_answer {
    private Integer id;
    private String answer_rubric;
    private String result;
    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer_rubric() {
        return answer_rubric;
    }

    public void setAnswer_rubric(String answer_rubric) {
        this.answer_rubric = answer_rubric;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
