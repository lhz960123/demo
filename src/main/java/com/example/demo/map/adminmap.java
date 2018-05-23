package com.example.demo.map;

import com.example.demo.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface adminmap {
    //管理员登录
    //登录验证select*from user where username=#{0} and password=#{1}  如果报位置错误就用该方法 或者用 @
    @Select("select * from admins where username=#{username}")
    public admin loginadmin_name(@Param("username") String username);
    @Select("select * from admins where password=#{password}")
    public admin loginadmin_password(String password);

}
