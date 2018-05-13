package com.example.demo.map;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    //查询选项内容
    @Select("select * from easyexams_option where id=#{id}")
    public easyexams_option easyexams_option_check(Integer id);
    //通过id修改成绩
    @Update("update easy_answer_grade set grade=#{grade} where id=#{id}")
    public int update_answer_grade(@Param("id") Integer id, @Param("grade") Integer grade);
    @Update("update easy_choose_grade set grade=#{grade} where id=#{id}")
    public int update_choose_grade(@Param("id") Integer id, @Param("grade") Integer grade);
}
