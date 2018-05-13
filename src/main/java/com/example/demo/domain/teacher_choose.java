package com.example.demo.domain;

public class teacher_choose {
    private Integer id;
    private String choose_rubric;
    private String option;
    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChoose_rubric() {
        return choose_rubric;
    }

    public void setChoose_rubric(String choose_rubric) {
        this.choose_rubric = choose_rubric;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
