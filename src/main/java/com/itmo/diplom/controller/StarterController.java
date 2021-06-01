package com.itmo.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StarterController {

    @GetMapping("/")
    public String starterPage(){
        return "starter/greeting";
    }
}
