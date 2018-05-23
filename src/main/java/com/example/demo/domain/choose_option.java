package com.example.demo.domain;

public class choose_option {
    //选择题号信息
    private  Integer choose_id;
    private  String rubric;
    //答案1题号信息
    private  Integer option_id1;
    private String option1;
    //答案2题号信息
    private  Integer option_id2;
    private String option2;
    //答案3题号信息
    private  Integer option_id3;
    private String option3;
    //对应导航链接
    private Integer qu_choose;

    public Integer getChoose_id() {
        return choose_id;
    }

    public void setChoose_id(Integer choose_id) {
        this.choose_id = choose_id;
    }

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
    }

    public Integer getOption_id1() {
        return option_id1;
    }

    public void setOption_id1(Integer option_id1) {
        this.option_id1 = option_id1;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public Integer getOption_id2() {
        return option_id2;
    }

    public void setOption_id2(Integer option_id2) {
        this.option_id2 = option_id2;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public Integer getOption_id3() {
        return option_id3;
    }

    public void setOption_id3(Integer option_id3) {
        this.option_id3 = option_id3;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public Integer getQu_choose() {
        return qu_choose;
    }

    public void setQu_choose(Integer qu_choose) {
        this.qu_choose = qu_choose;
    }
}
