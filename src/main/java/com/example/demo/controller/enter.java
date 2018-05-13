package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class enter {
    @RequestMapping({"/"})
    public String enter(){
        return "index";
    }
    @RequestMapping({"/list/list"})
    public String list(){
        return "list";
    }
}
