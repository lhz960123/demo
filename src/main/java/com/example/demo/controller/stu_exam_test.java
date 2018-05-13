package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.map.examsmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "examtest")
public class stu_exam_test {
    //记录选择题的id
    List<Integer> choose_id_num=new ArrayList<Integer>();
    //autowired
    @Autowired
    examsmap exams;
    @RequestMapping(value = "exam",method = RequestMethod.GET )
    public String exams(@RequestParam("id") String id, Model model, easy_choose_grade easy_choose_grade){
        List<easyexams_answer> list_answer=new ArrayList<easyexams_answer>();
        List<easyexams_choose> list_choosr=new ArrayList<easyexams_choose>();
        List<easyexams_option> list_option=new ArrayList<easyexams_option>();
        List<choose_option> list_c_o=new ArrayList<choose_option>();
        //选出题库所有题目
        list_answer=exams.select_easyexams_answer(Integer.parseInt(id));
        list_choosr=exams.select_easyexams_choose(Integer.parseInt(id));
        //在选择题里面挑10道题
           int ch=0;
           List<Integer> random=new ArrayList<Integer>();
           List<easyexams_choose> list_choosrnew=new ArrayList<easyexams_choose>();

       while (ch<10){
          int ran=(int)(Math.random()*list_choosr.size());
          if(random.size()!=0){
          for(int r=0;r<random.size();r++){
             if(ran==random.get(r)){
                 ran=-1;
            }
        }}
         if(ran==-1){
          }else{
          System.out.print(ran);
             random.add(ran);
             ch++;
             list_choosrnew.add(list_choosr.get(ran));
         }
   }
        //在简答题里面挑5道题
        int an=0;
        List<Integer> andom=new ArrayList<Integer>();
        List<easyexams_answer> list_answernew=new ArrayList<easyexams_answer>();
        while (an<5){
            int ran=(int)(Math.random()*list_answer.size());
            if(andom.size()!=0){
                for(int r=0;r<andom.size();r++){
                    if(ran==andom.get(r)){
                        ran=-1;
                    }
                }}
            if(ran==-1){
            }else{
                System.out.print(ran);
                andom.add(ran);
                an++;
                list_answernew.add(list_answer.get(ran));
            }
        }

        //把选择题与选择题选项连接在一起
        Integer in=1;
        for (easyexams_choose choose:list_choosrnew
             ) {
            choose_option choose_option=new choose_option();
            choose_option.setChoose_id(choose.getId());
            choose_option.setRubric(choose.getRubric());
            choose_id_num.add(choose_option.getChoose_id());
            list_option=exams.select_easyexams_option(choose.getId());
            choose_option.setChoose_id(in);
            for(int i=0;i<list_option.size();i++){
                if(i==0){
                    choose_option.setOption_id1(list_option.get(i).getId());
                    choose_option.setOption1(list_option.get(i).getOption());
                }
               else if(i==1){
                    choose_option.setOption_id2(list_option.get(i).getId());
                    choose_option.setOption2(list_option.get(i).getOption());
                }
                else {
                    choose_option.setOption_id3(list_option.get(i).getId());
                    choose_option.setOption3(list_option.get(i).getOption());
                }
            }
            in++;
            list_c_o.add(choose_option);

        }
        model.addAttribute("answer",list_answernew);
        model.addAttribute("c_o",list_c_o);
        return "exams";
    }

    @RequestMapping(value = "submit",method = RequestMethod.POST)
    public String submit(@RequestParam("answer") String answer_id, @RequestParam("result") String result, @RequestParam("choose") String chooser_id
            , @RequestParam(name = "1") String option1, @RequestParam(name = "2") String option2, @RequestParam(name = "3") String option3
            , @RequestParam(name = "4") String option4 , @RequestParam(name = "5") String option5 , @RequestParam(name = "6") String option6
            , @RequestParam(name = "8") String option7 , @RequestParam(name = "7") String option8, @RequestParam(name = "9") String option9
            , @RequestParam(name = "10") String option10, @RequestParam("student") String student_id
    ){

        String answers_id[]=answer_id.split(",");
        String results[]=result.split(",");
        for(int a=0;a<answers_id.length;a++){
            //进行去空格进行去大小写
            String s_r=results[a];
            //进行去空格
            s_r=s_r.replace(" ","");
            //返回新的小写
            String news_r=s_r.toLowerCase();
            //判断是否填写正确
            int tf=0;
            if(news_r.equals(exams.answer_tf(Integer.parseInt(answers_id[a])))){
                tf=1;
            }
         exams.easy_answer_save(Integer.parseInt(answers_id[a]),Integer.parseInt(student_id),results[a],tf);
        }
        for(int b=0;b<choose_id_num.size();b++){
            switch (b) {
                case 0: exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option1),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option1)));break;
                case 1:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option2),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option2)));break;
                case 2:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option3),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option3)));break;
                case 3:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option4),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option4)));break;
                case 4:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option5),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option5)));break;
                case 5:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option6),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option6)));break;
                case 6:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option7),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option7)));break;
                case 7:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option8),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option8)));break;
                case 8:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option9),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option9)));break;
                case 9:exams.easy_choose_save(choose_id_num.get(b),Integer.parseInt(option10),Integer.parseInt(student_id),exams.option_tf(Integer.parseInt(option10)));break;

            }
        }
        System.out.print(answer_id+result+ "  "+ answers_id[0]+results[0] +" 选择题"+choose_id_num.get(0)+option1   );
        return "fram";
    }
    //查询成绩
    @RequestMapping(value = "grade")
    public String grade(@RequestParam("student_id") String student_id, Model model){
        int sum=0;
        List<easy_answer_grade> easy_answer_grades=exams.easey_answer_check(Integer.parseInt(student_id));
        List<easy_answer_grade> easy_answer_gradesnew=new ArrayList<easy_answer_grade>();
        for (easy_answer_grade e:easy_answer_grades
             ) {if(e.getGrade()!=-1){
                 easy_answer_gradesnew.add(e);
        }
        }
        List<student_answer> list_student_answers=new ArrayList<student_answer>();
        for (easy_answer_grade e:easy_answer_gradesnew
             ) {student_answer student_answer=new student_answer();
                student_answer.setId(e.getId());
                student_answer.setAnswer_rubric(exams.easyexams_answer_check(e.getAnswer_id()).getRubric());
                student_answer.setResult(e.getResult());
                student_answer.setGrade(e.getGrade());
                sum+=e.getGrade();
                list_student_answers.add(student_answer);
        }

        List<easy_choose_grade> easy_choose_grades=exams.easey_choose_check(Integer.parseInt(student_id));
        List<easy_choose_grade> easy_choose_gradesnew=new ArrayList<easy_choose_grade>();
        for (easy_choose_grade e:easy_choose_grades
                ) {if(e.getGrade()!=-1){
            easy_choose_gradesnew.add(e);
        }
        }
        List<student_choose> list_student_chooses=new ArrayList<student_choose>();
        for (easy_choose_grade e:easy_choose_gradesnew
             ) {student_choose student_choose=new student_choose();
                student_choose.setId(e.getId());
                student_choose.setChoose_rubric(exams.easyexams_choose_check(e.getChoose_id()).getRubric());
                student_choose.setOption(exams.easyexams_option_check(e.getOption_id()).getOption());
                student_choose.setGrade(e.getGrade());
                sum+=e.getGrade();
                list_student_chooses.add(student_choose);
        }
        model.addAttribute("student_answer",list_student_answers);
        model.addAttribute("student_choose",list_student_chooses);
        model.addAttribute("sum",sum);
        return "studentsgrade";
    }
}
