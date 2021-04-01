package com.itmo.diplom.controllers;


import com.itmo.diplom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
    //@Autowired
    //private UserServiceImpl userService;

    @GetMapping("/greeting")
    public String greeting(){

        return "starter/greeting";
    }
    @GetMapping("/")
    public String root(){

        return "starter/greeting";
    }
}
