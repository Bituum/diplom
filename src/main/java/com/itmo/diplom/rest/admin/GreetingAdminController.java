package com.itmo.diplom.rest.admin;

import com.itmo.diplom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class GreetingAdminController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/all")
    public String userList(Model model){
        //model.addAttribute("allUsers", userService.getAllUser());
        return "admin/greeting";
    }

}
