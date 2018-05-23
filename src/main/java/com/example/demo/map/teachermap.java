package com.example.demo.map;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface teachermap {

    //登录验证select*from user where username=#{0} and password=#{1}  如果报位置错误就用该方法 或者用 @
    @Select("select * from teachers where name=#{name}")
    public teachers loginteachers_name(@Param("name") String name);
    @Select("select * from teachers where password=#{password}")
    public teachers loginteachers_password(String password);
    //通过成绩查询没有改的选择试题
    @Select("select * from easy_answer_grade where grade=#{grade}")
    public List<easy_answer_grade> easey_answer_check(Integer grade);
    //通过成绩查询没有改的选择题
    @Select("select * from easy_choose_grade where grade=#{grade}")
    public List<easy_choose_grade> easey_choose_check(Integer grade);
    //查询填空题提干
    @Select("select * from easyexams_answer where id=#{id}")
    public easyexams_answer easyexams_answer_check(Integer id);
    //查询选择题提干
    @Select("select * from easyexams_choose where id=#{id}")
    public easyexams_choose easyexams_choose_check(Integer id);
    //查询选择题id
    @Select("select * from easyexams_choose where rubric=#{rubric}")
    public easyexams_choose easyexams_choose_check_S(String rubric);
    //查询选项内容
    @Select("select * from easyexams_option where id=#{id}")
    public easyexams_option easyexams_option_check(Integer id);
    //通过id修改成绩
    @Update("update easy_answer_grade set grade=#{grade} where id=#{id}")
    public int update_answer_grade(@Param("id") Integer id, @Param("grade") Integer grade);
    @Update("update easy_choose_grade set grade=#{grade} where id=#{id}")
    public int update_choose_grade(@Param("id") Integer id, @Param("grade") Integer grade);
    //添加一条填空题
    @Insert("insert into easyexams_answer(rubric,tf,subject) values(#{rubric},#{tf},#{subject})")
    public int up_answer(@Param("rubric") String rubric,@Param("tf") String tf,@Param("subject") Integer subject);
    //添加一条选择题目
    @Insert("insert into easyexams_choose(rubric,subject) values(#{rubric},#{subject})")
    public int up_choose(@Param("rubric") String rubric,@Param("subject") Integer subject);
    //添加一条选择题选项
    @Insert("insert into easyexams_option(options,choose,tf) values(#{options},#{choose},#{tf})")
    public int up_option(@Param("options") String option,@Param("choose") Integer choose,@Param("tf") Integer tf);
    //根据名称去查询类型id
    @Select("select * from subject where subjectname=#{subjectname}")
    public subject select_subject(@Param("subjectname") String subjectname);
}
