package com.example.demo.controller;

import com.example.demo.domain.*;
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

@Controller
@RequestMapping(value = "teacher")
public class teacher_index {
    @Autowired
    teachermap t;
    @Autowired
    examsmap exams;
    @Autowired
    studentsmap s;
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
                List<subject> list=new ArrayList<subject>();
                list=exams.subjectall();
                session.setAttribute("subject",list);
                //model.addAttribute("choose",t.easey_choose_check(-1));
                return "teacherwel";
            }else{
                model.addAttribute("warning","请检查密码");

                return "/";
            }
        }else{
            //登录失败
            model.addAttribute("warning","请检查用户名");
            return "/";
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
    //入口
    @RequestMapping(value = "upa" ,method = RequestMethod.GET)
    public  String upa(){ return  "teacherinanswer";}
    //上传单给填空题
    @RequestMapping(value = "upanswer" ,method = RequestMethod.GET)
    public String upanswer(@RequestParam("up_answer") String up_answer,@RequestParam("up_answer_tf") String up_tf,@RequestParam("id") Integer subject_id){
            t.up_answer(up_answer,up_tf,subject_id);
            return "teacherinanswer";
    }
    //入口
    @RequestMapping(value = "upc" ,method = RequestMethod.GET)
    public String upc(){return "teacherinchoose";}
    //上传单给选择题
    @RequestMapping(value = "upchoose" ,method = RequestMethod.GET)
    public String upchoose(@RequestParam("up_choose") String up_answer,@RequestParam("up_option1") String up_option1,@RequestParam("up_option2") String up_option2,@RequestParam("up_option3") String up_option3,@RequestParam("up_option_tf") String up_option_tf,@RequestParam("id") Integer subject_id){
        t.up_choose(up_answer,subject_id);
        easyexams_choose easyexams_choose=t.easyexams_choose_check_S(up_answer);
        if(up_option1.equals(up_option_tf)){
            t.up_option(up_option1,easyexams_choose.getId(),2);
            t.up_option(up_option2,easyexams_choose.getId(),1);
            t.up_option(up_option3,easyexams_choose.getId(),1);
        }else if(up_option2.equals(up_option_tf)){
            t.up_option(up_option1,easyexams_choose.getId(),1);
            t.up_option(up_option2,easyexams_choose.getId(),2);
            t.up_option(up_option3,easyexams_choose.getId(),1);
        }else {
            t.up_option(up_option1,easyexams_choose.getId(),1);
            t.up_option(up_option2,easyexams_choose.getId(),1);
            t.up_option(up_option3,easyexams_choose.getId(),3);
        }
        return "teacherinchoose";
    }
    //查询入口
    @RequestMapping(value = "tl" ,method = RequestMethod.GET)
    public String tl(){return  "teacherlook";}
    //查询学生成绩功能
    @RequestMapping(value = "teacherlist" ,method = RequestMethod.GET)
    public String teacherlist(@RequestParam("username")String username,@RequestParam("id") String subject_id, Model model){
        students students=new students();
        List<exam_show> list_show=new ArrayList<exam_show>();
        students=s.loginstudents_name(username);
        if (students!=null){
            //填空题
            List<easy_answer_grade> easy_answer_grades=exams.easey_answer_check(students.getId());
            if(easy_answer_grades.size()!=0){
                List<student_answer> list_student_answers=new ArrayList<student_answer>();
                for (easy_answer_grade e:easy_answer_grades){
                    if(exams.easyexams_answer_check(e.getAnswer_id()).getSubject()==Integer.parseInt(subject_id)){
                     student_answer sa=new student_answer();
                     sa.setId(e.getId());
                     sa.setAnswer_rubric(exams.easyexams_answer_check(e.getAnswer_id()).getRubric());
                     sa.setResult(e.getResult());
                     sa.setTf(exams.easyexams_answer_check(e.getAnswer_id()).getTf());
                     sa.setGrade(e.getGrade()==0?"错误":"正确");
                     list_student_answers.add(sa);}
                }
                for (student_answer s:list_student_answers){
                    exam_show exam_show=new exam_show();
                    exam_show.setRubric("填空题："+s.getAnswer_rubric());
                    exam_show.setResult(s.getResult());
                    exam_show.setTf(s.getTf());
                    exam_show.setGrade(s.getGrade());
                    list_show.add(exam_show);
                }

            }
            //选择题
            List<easy_choose_grade> easy_choose_grades=exams.easey_choose_check(students.getId());
            if(easy_choose_grades.size()!=0){
                List<student_choose> list_student_chooses=new ArrayList<student_choose>();
                for (easy_choose_grade e:easy_choose_grades
                        ) {
                    if(exams.easyexams_choose_check(e.getChoose_id()).getSubject()==Integer.parseInt(subject_id)){
                    student_choose student_choose=new student_choose();
                    student_choose.setId(e.getId());
                    student_choose.setChoose_rubric(exams.easyexams_choose_check(e.getChoose_id()).getRubric());
                    student_choose.setOption(exams.easyexams_option_check(e.getOption_id()).getOption());
                    List<easyexams_option> optionList=exams.select_easyexams_option(e.getChoose_id());
                    for(easyexams_option eo:optionList){
                         if(eo.getTf()==2){
                             student_choose.setTf(eo.getOption());
                         }
                    }
                    student_choose.setGrade(e.getGrade()!=1?"正确":"错误");
                    list_student_chooses.add(student_choose);
                }}
                for(student_choose s:list_student_chooses){
                    exam_show exam_show=new exam_show();
                    exam_show.setRubric("选择题："+s.getChoose_rubric());
                    exam_show.setResult(s.getOption());
                    exam_show.setTf(s.getTf());
                    exam_show.setGrade(s.getGrade());
                    list_show.add(exam_show);
                }

            }
            model.addAttribute("exam_show",list_show);
            return "teacherlook";
        }
        model.addAttribute("warring","不存在此学生");
        return "teacherlook";
    }
}
