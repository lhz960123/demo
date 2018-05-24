package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.domain.subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.map.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "ad")
public class admin_index {
    @Autowired
    adminmap a;
    @Autowired
    studentsmap s;
    @Autowired
    teachermap t;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("adminname") String username, @RequestParam("adminpassword") String password, Model model) {
        admin admin = new admin();
        admin = a.loginadmin_name(username);
        if (admin != null) {
            if (password.equals(admin.getPassword())) {
                //model.addAttribute("users",st.getName());
                //跳转到其他页面
                return "adminok";
            }
        } else {
            //登录失败
            model.addAttribute("warning", "请检查用户名");
            return "login";
        }
        return "adminok";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "adminaddstudent";
    }

    @RequestMapping(value = "registerstudents", method = RequestMethod.GET)
    public String registerstudents(@RequestParam("username") String username, @RequestParam("password") String password) {
        s.insertstudents(username, password);
        return "adminaddstudent";
    }

    @RequestMapping(value = "updatestudent", method = RequestMethod.GET)
    public String updatestudent() {
        return "adminupdatestudent";
    }

    @RequestMapping(value = "selectstudent", method = RequestMethod.GET)
    public String selectstudent(@RequestParam("username") String username, Model model) {
        students students = new students();
        students = s.loginstudents_name(username);
        model.addAttribute("studentid", students.getId());
        model.addAttribute("studentname", students.getName());
        model.addAttribute("studentpassword", students.getPassword());
        return "adminupdatestudent";
    }

    @RequestMapping(value = "newpasswordstudent", method = RequestMethod.GET)
    public String newpasswordstudent(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("studentid") String id, Model model) {
        students students = new students();
        students.setName(username);
        students.setPassword(password);
        students.setId(Integer.parseInt(id));
        s.updatastudents(students);
        return "adminupdatestudent";
    }

    @RequestMapping(value = "examstudent", method = RequestMethod.GET)
    public String examstudent() {
        return "adminexam";
    }

    @RequestMapping(value = "selectexam", method = RequestMethod.GET)
    public String selectexam(@RequestParam("username") String username, Model model) {
        students students = new students();
        students = s.loginstudents_name(username);
        model.addAttribute("studentid", students.getId());
        model.addAttribute("studentname", students.getName());
        if (students.getRights() == 0) {
            model.addAttribute("warning", "没有考试次数");
        } else {
            model.addAttribute("success", "拥有考试次数");
        }
        return "adminexam";
    }

    @RequestMapping(value = "addexam", method = RequestMethod.GET)
    public String addexam(@RequestParam("username") String username, @RequestParam("studentid") String id, Model model) {
        students students = new students();
        students.setId(Integer.parseInt(id));
        students.setRights(1);
        s.updatastudents_exam(students);
        return "adminexam";
    }

    @RequestMapping(value = "registerteacher", method = RequestMethod.GET)
    public String registerteacher() {
        return "adminaddteacher";
    }

    @RequestMapping(value = "addteacher", method = RequestMethod.GET)
    public String addstudents(@RequestParam("username") String username, @RequestParam("password") String password) {
        t.insertteacher(username, password);
        return "adminaddteacher";
    }

    @RequestMapping(value = "updateteacher", method = RequestMethod.GET)
    public String updateteacher() {
        return "adminupdateteacher";
    }

    @RequestMapping(value = "selectteacher", method = RequestMethod.GET)
    public String selectteacher(@RequestParam("username") String username, Model model) {
        teachers teacher = new teachers();
        teacher = t.loginteachers_name(username);
        model.addAttribute("teacherid", teacher.getId());
        model.addAttribute("teachername", teacher.getName());
        model.addAttribute("teacherpassword", teacher.getPassword());
        return "adminupdateteacher";
    }

    @RequestMapping(value = "newpasswordteacher", method = RequestMethod.GET)
    public String newpasswordteacher(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("teachertid") String id, Model model) {
        teachers teacher = new teachers();
        teacher.setId(Integer.parseInt(id));
        teacher.setName(username);
        teacher.setPassword(password);
        t.updatateacher(teacher);
        return "adminupdateteacher";
    }
}
