package com.itmo.diplom.controllers;


import com.itmo.diplom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class GreetingController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/hello")
    public String greeting(){

        return "greeting";
    }
}
