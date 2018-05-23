package com.example.demo.controller;

import com.example.demo.domain.easyexams_answer;
import com.example.demo.domain.easyexams_choose;
import com.example.demo.domain.subject;
import com.example.demo.map.teachermap;
import com.example.demo.tool.ImportExcel;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "excel",method = RequestMethod.POST)
public class excel_up {
    @Autowired
    teachermap t;
    @RequestMapping(value = "up",method = RequestMethod.POST)
    public String up(@RequestParam("upfile") MultipartFile file,HttpServletRequest httpServletRequest) throws  Exception{
        System.out.println("通过传统方式form");
        InputStream in=null;
        List<List<Object>> listob=null;
        MultipartFile multipartFile=file;
        if (multipartFile.isEmpty()){
            throw  new Exception("文件不存在");
        }
        in = multipartFile.getInputStream();
        String finame=multipartFile.getOriginalFilename();
        System.out.print(multipartFile.getName()+"  "+finame);
        listob = new ImportExcel().getBankListByExcel(in,finame);
        //循环Excel表格进行插入数据
        for(int i=0;i<listob.size();i++){
           List<Object> list= listob.get(i);
           easyexams_answer e=new easyexams_answer();
           e.setRubric(String.valueOf(list.get(0)));
           e.setTf(String.valueOf(list.get(1)));
           String s= String.valueOf(list.get(2));
           subject su=t.select_subject(s);
           Integer su_id=su.getId();
           e.setSubject(su_id);
           t.up_answer(e.getRubric(),e.getTf(),e.getSubject());
        }
        return "teacherwork";
    }
    public String up_choose(@RequestParam("upfile_choose") MultipartFile file,HttpServletRequest httpServletRequest) throws  Exception{
        System.out.println("通过传统方式form");
        InputStream in=null;
        List<List<Object>> listob=null;
        MultipartFile multipartFile=file;
        if (multipartFile.isEmpty()){
            throw  new Exception("文件不存在");
        }
        in = multipartFile.getInputStream();
        String finame=multipartFile.getOriginalFilename();
        System.out.print(multipartFile.getName()+"  "+finame);
        listob = new ImportExcel().getBankListByExcel(in,finame);
        for(int i=0;i<listob.size();i++){
            List<Object> list= listob.get(i);
            easyexams_choose e=new easyexams_choose();
            e.setRubric(String.valueOf(list.get(0)));
            e.setSubject(t.select_subject(String.valueOf(list.get(1))).getId());
            t.up_choose(e.getRubric(),e.getSubject());
            easyexams_choose easyexams_choose=t.easyexams_choose_check_S(e.getRubric());
            if(String.valueOf(list.get(3)).equals(String.valueOf(list.get(6)))){
                t.up_option(String.valueOf(list.get(3)),easyexams_choose.getId(),2);
                t.up_option(String.valueOf(list.get(4)),easyexams_choose.getId(),1);
                t.up_option(String.valueOf(list.get(5)),easyexams_choose.getId(),1);
            }else if(String.valueOf(4).equals(String.valueOf(6))){
                t.up_option(String.valueOf(list.get(3)),easyexams_choose.getId(),1);
                t.up_option(String.valueOf(list.get(4)),easyexams_choose.getId(),2);
                t.up_option(String.valueOf(list.get(5)),easyexams_choose.getId(),1);
            }else {
                t.up_option(String.valueOf(list.get(3)),easyexams_choose.getId(),1);
                t.up_option(String.valueOf(list.get(4)),easyexams_choose.getId(),1);
                t.up_option(String.valueOf(list.get(5)),easyexams_choose.getId(),3);
            }

        }
        return "teacherwork";
    }

}
