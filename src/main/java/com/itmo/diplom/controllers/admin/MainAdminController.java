package com.itmo.diplom.controllers.admin;

import com.itmo.diplom.entities.UserEntity;
import com.itmo.diplom.service.UserService;
import com.itmo.diplom.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainAdminController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admin")
    public String mainPanel(){
        return "admin/greeting";
    }

    @GetMapping("admin/users/{id}")
    public String getOneUser(@PathVariable int id, Model model){
         model.addAttribute("user", userService.getUser(id));
         return "admin/oneUser";
    }

    @GetMapping("admin/users/all")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUser());
        return "admin/showAll";
    }
    @GetMapping("admin/users/edit/{id}")
    public String changeUser(Model model, @PathVariable("id") int userId){
        model.addAttribute("userForm", userService.getUser(userId));
        return "admin/changeUser";
    }
    @PostMapping("/admin/users/edit/{id}")
    public String changeUser(@ModelAttribute("userForm") @Valid UserEntity userForm,
                             @PathVariable("id") int userId, Model model, BindingResult result){
        if(result.hasErrors()){
            //TODO valid form
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "admin/changeUser";
        }
        if(!userService.save(userForm)){
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "/admin/changeUser";
        }
        //userService.deleteUser(userId);
        return "redirect:/admin/users/all";
    }

    @PostMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return "redirect:/admin/users/all";
    }
    @GetMapping("/admin/users/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new UserEntity());
        return "admin/registration";
    }

    @PostMapping("/admin/users/registration")
    public String addNewUser(@ModelAttribute("userForm") @Valid UserEntity userForm, Model model, BindingResult result){
        if(result.hasErrors()){
            return "starter/registration";
        }
        if(!userService.save(userForm)){
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "starter/registration";
        }
        return "redirect:/admin/users/all";

    }

}
