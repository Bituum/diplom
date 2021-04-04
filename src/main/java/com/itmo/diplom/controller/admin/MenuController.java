package com.itmo.diplom.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/admin/menu")
    public String adminMenu(){
        return "admin/menu";
    }
}
