package com.example.demo.map;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface examsmap {
    //查询所有的分科
    @Select("select * from subject")
    public List<subject> subjectall();
    //通过分科查询所有的简单简答题
    @Select("select * from easyexams_answer where subject=#{subject}")
    public List<easyexams_answer> select_easyexams_answer(Integer subject);
    //通过分科查询所有的简单选择题
    @Select("select * from easyexams_choose where subject=#{subject}")
    public List<easyexams_choose> select_easyexams_choose(Integer subject);
    //通过分科查询所有的简单选择题选项
    @Select("select * from easyexams_option where choose=#{choose}")
    public List<easyexams_option> select_easyexams_option(Integer choose);
    //插入一条简答题数据
    @Insert("insert into easy_answer_grade(answer_id,student_id,result,grade) values(#{answer_id},#{student_id},#{result},#{grade})")
    public int easy_answer_save(@Param("answer_id") Integer answer_id, @Param("student_id") Integer student_id, @Param("result") String result,@Param("grade") Integer grade);
    //查询选择题的选项是否是正确选项
    @Select("select tf from easyexams_option where id=#{id}")
    public Integer option_tf(Integer id);
    //查询填空题的真正答案
    @Select("select tf from easyexams_answer where id=#{id}")
    public String answer_tf(Integer id);
    //插入一条选择题数据
    @Insert("insert into easy_choose_grade(choose_id,option_id,student_id,grade) values(#{choose_id},#{option_id},#{student_id},#{grade})")
    public int easy_choose_save(@Param("choose_id") Integer choose_id, @Param("option_id") Integer option_id, @Param("student_id") Integer student_id,@Param("grade") Integer grade);
    //根据学生id去查询成绩
    @Select("select * from easy_answer_grade where student_id=#{student_id}")
    public List<easy_answer_grade> easey_answer_check(Integer student_id);
    @Select("select * from easy_choose_grade where student_id=#{student_id}")
    public List<easy_choose_grade> easey_choose_check(Integer student_id);
    @Select("select * from easyexams_answer where id=#{id}")
    public easyexams_answer easyexams_answer_check(Integer id);
    //查询选择题提干
    @Select("select * from easyexams_choose where id=#{id}")
    public easyexams_choose easyexams_choose_check(Integer id);
    //查询选项内容
    @Select("select * from easyexams_option where id=#{id}")
    public easyexams_option easyexams_option_check(Integer id);

}
