package com.itmo.diplom.rest.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/admin/menu")
    public String adminMenu(){
        return "admin/menu";
    }
}
