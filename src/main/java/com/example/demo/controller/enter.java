package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class enter {
    @RequestMapping({"/"})
    public String enter(){
        return "index";
    }
    @RequestMapping({"/fram"})
    public String fram(){
        return "fram";
    }
    @RequestMapping({"/admin"})
    public String admin(){return  "admin";}
    @RequestMapping({"/list/list"})
    public String list(){
        return "list";
    }
}
