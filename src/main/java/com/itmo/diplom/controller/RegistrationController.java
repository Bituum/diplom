package com.itmo.diplom.controller;


import com.itmo.diplom.entity.UserEntity;
import com.itmo.diplom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new UserEntity());
        return "starter/registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute("userForm") @Valid UserEntity userForm, Model model, BindingResult result){
        if(result.hasErrors()){
            return "starter/registration";
        }
        if(!userService.save(userForm)){
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "starter/registration";
        }
        return "redirect:/";

    }


}
