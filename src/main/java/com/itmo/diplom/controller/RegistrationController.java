package com.itmo.diplom.controller;


import com.itmo.diplom.entity.UserEntity;
import com.itmo.diplom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public String addNewUser(@Valid @ModelAttribute("userForm") UserEntity userForm,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            return "starter/registration";
        }
        try{
            userService.save(userForm);
        }catch (IllegalArgumentException usernameNotFoundException){
            model.addAttribute("usernameNotFound", 1);
            return "starter/registration";
        }
        return "redirect:/";
    }


}
