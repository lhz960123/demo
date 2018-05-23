package com.example.demo.map;

import com.example.demo.domain.students;
import org.apache.ibatis.annotations.*;

@Mapper
public interface studentsmap {
    //通过id查询单个学生信息
    @Select("select * from students where id=#{id}")
    public students getstudents(int id);
    //登录验证select*from user where username=#{0} and password=#{1}  如果报位置错误就用该方法 或者用 @
    @Select("select * from students where name=#{name}")
    public students loginstudents_name(@Param("name") String name);
    @Select("select * from students where password=#{password}")
    public students loginstudents_password(String password);
    //注册一个新的学生
    @Insert("insert into students(name,password) values(#{name},#{password})")
    public int insertstudents(@Param("name") String name, @Param("password") String password);
    //修改一个学生的密码
    @Update("update students set password=#{password} where id=#{id}")
    public int updatastudents(students students);
    //添加考试
    @Update("update students set rights=#{rights} where id=#{id}")
    public int updatastudents_exam(students students);
    //删除一个学生信息
    @Delete("delete from students where id=#{id}")
    public int deletestudents(Integer id);
}
