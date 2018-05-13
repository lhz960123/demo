package com.example.demo.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "excel",method = RequestMethod.POST)
public class excel_up {
    @RequestMapping(value = "up",method = RequestMethod.POST)
    public String up(HttpServletRequest httpServletRequest) throws  Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) httpServletRequest;
        System.out.println("通过传统方式form");
        InputStream in=null;
        List<List<Object>> listob=null;
        MultipartFile multipartFile=multipartRequest.getFile("upfile");
        return "";
    }

}
