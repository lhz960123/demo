package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.map.teachermap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "teacher")
public class teacher_index {
    @Autowired
    teachermap t;
    //登录接口
    @RequestMapping(value = "login" ,method = RequestMethod.GET)
    public String login(){
        return "teacher_login";
    }
    @RequestMapping(value = "lg" ,method = RequestMethod.POST)
    public String lg(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session){
        teachers te=new teachers();
        te=t.loginteachers_name(username);
        if (te!=null){
            if(password.equals(te.getPassword())){
                //model.addAttribute("users",st.getName());
                //跳转到其他页面
                session.setAttribute("teacher",te);
                //model.addAttribute("choose",t.easey_choose_check(-1));
                //填空题
                List<teacher_answer> list_answer=new ArrayList<teacher_answer>();
                List<easy_answer_grade> answer=t.easey_answer_check(-1);
                for (easy_answer_grade e:answer
                     ) {teacher_answer teacher_answer=new teacher_answer();
                        easyexams_answer easyexams_answer=t.easyexams_answer_check(e.getAnswer_id());
                        teacher_answer.setId(e.getId());
                        teacher_answer.setAnswer_rubric(easyexams_answer.getRubric());
                        teacher_answer.setResult(e.getResult());
                        list_answer.add(teacher_answer);
                }
                model.addAttribute("list_answer",list_answer);
                //选择题
                List<teacher_choose> list_choose=new ArrayList<teacher_choose>();
                List<easy_choose_grade> choose=t.easey_choose_check(-1);
                for (easy_choose_grade e:choose
                        ) {teacher_choose teacher_choose=new teacher_choose();
                    easyexams_choose easyexams_choose=t.easyexams_choose_check(e.getChoose_id());
                    easyexams_option easyexams_option=t.easyexams_option_check(e.getOption_id());
                    teacher_choose.setId(e.getId());
                    teacher_choose.setChoose_rubric(easyexams_choose.getRubric());
                    teacher_choose.setOption(easyexams_option.getOption());
                    list_choose.add(teacher_choose);
                }
                model.addAttribute("list_answer",list_answer);
                model.addAttribute("list_choose",list_choose);
                if(list_answer.size()==0&&list_choose.size()==0){
                    model.addAttribute("warning","没有学生有考试");
                }
                return "teachersok";
            }else{
                model.addAttribute("warning","请检查密码");

                return "teacher_login";
            }
        }else{
            //登录失败
            model.addAttribute("warning","请检查用户名");
            return "teacher_login";
        }


    }
    @RequestMapping(value = "gradeok",method = RequestMethod.POST)
    public String gradeok(@RequestParam("answer_id") String answer_id, @RequestParam("answer_grade") String answer_grade, @RequestParam("choose_id") String choose_id, @RequestParam("choose_grade") String choose_grade){
        //System.out.print(answer_id+answer_grade+choose_id+choose_grade);
        String answers_id[]=answer_id.split(",");
        String answers_grade[]=answer_grade.split(",");
        String chooses_id[]=choose_id.split(",");
        String chooses_grade[]=choose_grade.split(",");
        for (int j=0;j<answers_id.length;j++){
            t.update_answer_grade(Integer.parseInt(answers_id[j]),Integer.parseInt(answers_grade[j]));
        }
        for (int j=0;j<chooses_id.length;j++){
            t.update_choose_grade(Integer.parseInt(chooses_id[j]),Integer.parseInt(chooses_grade[j]));
        }
        return "teacher_login";
    }
}
