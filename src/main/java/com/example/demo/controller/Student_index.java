package com.example.demo.controller;

import com.example.demo.domain.students;
import com.example.demo.domain.subject;
import com.example.demo.map.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Controller
@RequestMapping(value = "web")
public class Student_index {
    @Autowired
    studentsmap s;
    @Autowired
    examsmap exam;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String hello() {
        return "login";
    }

    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String sl(@RequestParam("Id") Integer id, Model model) {
        students st = new students();
        st = s.getstudents(id);
        model.addAttribute("users", st.getName());
        return "studentsok";
    }

    @RequestMapping(value = "lg", method = RequestMethod.POST)
    public String loginstudents(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        students st = new students();
        st = s.loginstudents_name(username);
        if (st != null) {
            if (password.equals(st.getPassword())) {
                //model.addAttribute("users",st.getName());
                //跳转到其他页面
                session.setAttribute("student", st);
                List<subject> list = new ArrayList<subject>();
                list = exam.subjectall();
                session.setAttribute("subject", list);
                return "studentwel";
            } else {
                model.addAttribute("warning", "请检查密码");
                return "index";
            }
        } else {
            //登录失败
            model.addAttribute("warning", "请检查用户名");
            return "index";
        }

    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "registerstudents", method = RequestMethod.GET)
    public String registerstudents(@RequestParam("username") String username, @RequestParam("password") String password) {
        s.insertstudents(username, password);
        return "register";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update() {
        return "update";
    }

    @RequestMapping(value = "updatestudents", method = RequestMethod.GET)
    public String updatestudents(@RequestParam("username") String username, @RequestParam("password") String password) {
        students students = new students();
        students = s.loginstudents_name(username);
        students.setPassword(password);
        s.updatastudents(students);
        return "update";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete() {
        return "delete";
    }

    @RequestMapping(value = "deletestudents", method = RequestMethod.GET)
    public String deletestudents(@RequestParam("id") String id) {
        s.deletestudents(Integer.parseInt(id));
        return "delete";
    }

    @RequestMapping(value = "exam.htm")
    public String examPage() {
        return "studentexam";
    }

    @RequestMapping(value = "query.htm")
    public String query() {
        return "studentlook";
    }
}
