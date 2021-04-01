package com.itmo.diplom.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDishesController {
    @GetMapping("/admin/dishes")
    public String adminMenu(){
        return "admin/dishes";
    }
    //TODO: mappings for all dishes and menu

}
