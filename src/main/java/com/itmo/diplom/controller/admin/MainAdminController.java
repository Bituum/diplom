package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.UserEntity;
import com.itmo.diplom.entity.UserPropertiesEntity;
import com.itmo.diplom.service.UserPropertiesServiceImpl;
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

    @Autowired
    private UserPropertiesServiceImpl userPropertiesService;

//    @GetMapping("/admin")
//    public String mainPanel(){
//        return "admin/user/greeting";
//    }

    /*@GetMapping("admin/users/{id}")
    public String getOneUser(@PathVariable int id, Model model){
         model.addAttribute("user", userService.getUser(id));
         return "admin/oneUser";
    }*/

    @GetMapping("admin/users/all")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUser());
        return "admin/user/showAll";
    }
    @GetMapping("admin/users/edit/{id}")
    public String changeUser(Model model, @PathVariable("id") int userId){
        model.addAttribute("userForm", userService.getUser(userId));
        return "admin/user/changeUser";
    }
    @PostMapping("/admin/users/edit/{id}")
    public String changeUser(@ModelAttribute("userForm") @Valid UserEntity userForm, Model model, BindingResult result ){
        if(result.hasErrors()){
            //TODO valid form
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "admin/user/changeUser";
        }
        if(!userService.save(userForm)){
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "/admin/user/changeUser";
        }
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
        return "admin/user/registration";
    }

    @PostMapping("/admin/users/registration")
    public String addNewUser(@ModelAttribute("userForm") @Valid UserEntity userForm, Model model, BindingResult result){
        if(result.hasErrors()){
            return "admin/user/registration";
        }
        if(!userService.save(userForm)){
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "admin/user/registration";
        }
        return "redirect:/admin/users/all";

    }

    @GetMapping("/admin/users/{id}/properties")
    public String showUserProperties(@PathVariable("id") int id, Model model){
        try{
            model.addAttribute("pid", id);
            UserPropertiesEntity userProperties = userPropertiesService.getUserProperty(id);
            model.addAttribute("propertyForm", userProperties);
            return "admin/user/oneUser";
        }catch (IllegalArgumentException empty){
            model.addAttribute("newPropertyForm", new UserPropertiesEntity());
            System.out.println("bag of d**cks");

            return "admin/user/newUserProperty";
        }
    }

   /* @GetMapping("/admin/users/properties/create")
    public String createNewUserProperties(Model model){
        model.addAttribute("newPropertyForm", new UserPropertiesEntity());
        return "admin/user/newUserProperty";
    }

    @PostMapping("/admin/users/properties/create")
    public String createNewUserProperties(@ModelAttribute("propertyForm") @Valid UserPropertiesEntity userProperties, Model model, BindingResult result){
        if(result.hasErrors()){
            return "admin/user/newUserProperty";
        }
        if(!userPropertiesService.save(userProperties)){
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "admin/user/newUserProperty";
        }
        return "redirect:/admin/users/all";
    }*/

    @GetMapping("/admin/users/properties/edit/{id}")
    public String changeUserProperties(Model model, @PathVariable("id") int id){
        if (userPropertiesService.getUserProperty(id) != null){
            model.addAttribute("pid", id);
            model.addAttribute("newPropertyForm", userPropertiesService.getUserProperty(id));
            System.out.println("YAY");
        }
        else
        {
            model.addAttribute("newPropertyForm", new UserPropertiesEntity());
            System.out.println("Bag of d**ks");
        }
        return "admin/user/newUserProperty";
    }

    @PostMapping("/admin/users/properties/edit/{id}")
    public String changeUserProperties(@ModelAttribute("newPropertyForm") @Valid UserPropertiesEntity userProperties, Model model, BindingResult result, @PathVariable("id") int id){
        if(result.hasErrors()){
            return "admin/user/newUserProperty";
        }
        System.out.println(userProperties);
        System.out.println(userProperties.getUserId());
        if(!userPropertiesService.save(userProperties)){
            //model.addAttribute("usernameError", "Username this that login is existing");
            return "admin/user/newUserProperty";
        }
        //TODO: make try catch
        return "redirect:/admin/users/all";
    }
}
