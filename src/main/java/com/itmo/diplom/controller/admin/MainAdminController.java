package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.entity.UserEntity;
import com.itmo.diplom.entity.UserPropertiesEntity;
import com.itmo.diplom.entity.UserWorktimeEntity;
import com.itmo.diplom.service.*;
import com.itmo.diplom.util.InitActiveDishes;
import com.itmo.diplom.util.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class MainAdminController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserPropertiesServiceImpl userPropertiesService;

    @Autowired
    private UserWorktimeServiceImpl userWorktimeService;

    @Autowired
    private DishesServiceImpl dishesService;

    private static Logger logger = Logger.getLogger(MainAdminController.class.getName());
//    @GetMapping("/admin")
//    public String mainPanel(){
//        return "admin/user/greeting";
//    }

    /*@GetMapping("admin/users/{id}")
    public String getOneUser(@PathVariable int id, Model model){
         model.addAttribute("user", userService.getUser(id));
         return "admin/oneUser";
    }*/

    private LocalTime timeChanger(String str) throws ParseException {
        return LocalTime.parse(str);
    }

    private LocalTime zeroTime(){
        return LocalTime.ofNanoOfDay(0);
    }

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
    }

    @GetMapping("admin/users/all")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUser());
        initActiveDishes(model);
        return "admin/user/showAll";
    }
    @GetMapping("admin/users/edit/{id}")
    public String changeUser(Model model, @PathVariable("id") int userId){
        model.addAttribute("userForm", userService.getUser(userId));
        initActiveDishes(model);
        return "admin/user/changeUser";
    }
    @PostMapping("/admin/users/edit/{id}")
    public String changeUser(@ModelAttribute("userForm") @Valid UserEntity userForm, BindingResult result ){
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
        initActiveDishes(model);
        return "admin/user/registration";
    }

    @PostMapping("/admin/users/registration")
    public String addNewUser(@ModelAttribute("userForm") @Valid UserEntity userForm, BindingResult result){
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
            initActiveDishes(model);
            return "admin/user/oneUser";
        }catch (IllegalArgumentException empty){
            model.addAttribute("newPropertyForm", new UserPropertiesEntity());
            System.out.println("bag of d**cks");
            initActiveDishes(model);
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
            initActiveDishes(model);
        }
        else
        {
            model.addAttribute("newPropertyForm", new UserPropertiesEntity());
            System.out.println("Bag of d**ks");
            initActiveDishes(model);
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

    @GetMapping("admin/time_manager")
    public String showTime(Model model){
        List<UserWorktimeEntity> currentWorkTimeUsers = userWorktimeService.getAllUserWorkTime();
        model.addAttribute("names", userService.getAllUser().stream().filter(x -> {
            for (UserWorktimeEntity work : currentWorkTimeUsers) {
                if(work.getId().equals(x.getId())){
                    return true;
                }
            }
            return false;
        })
                .map(UserEntity::getLogin)
                .collect(Collectors.toList()));


        model.addAttribute("workers", userWorktimeService.getAllUserWorkTime());
        List<UserWorktimeEntity> list = userWorktimeService.getAllUserWorkTime();
        List<LocalTime> currentTimes = new ArrayList<>();
        StopWatch watch = new StopWatch();
        for(UserWorktimeEntity worktimeEntity : list){
            LocalTime time = worktimeEntity.getStartTime();

            logger.info("START TIME IN SECONDS -----> " + time.toSecondOfDay());
            long tmpSeconds = time.toSecondOfDay();
            logger.info("TMP TIME IN SECONDS -----> " + tmpSeconds);
            currentTimes.add(watch.getElapsedTime(tmpSeconds));
        }
        model.addAttribute("currentTime", currentTimes);

        //TODO цикл по пользователям, которые сейчас на смене и передать время этихх работников
        initActiveDishes(model);
        return "/admin/user/showTime";
    }

    @GetMapping("/admin/time_manager/choose")
    public String chooseUsers(Model model){
        List<Integer> listI = new ArrayList<>();

        for (UserEntity u : userService.getAllUser()) {
            listI.add(u.getId());
        }

        model.addAttribute("allUsersID", userService.getAllUser().stream().map(UserEntity::getId).collect(Collectors.toList()));
        model.addAttribute("strUser", userService.getAllUser().stream().map(x -> x.getLogin()).collect(Collectors.toList()));
        initActiveDishes(model);
        return "/admin/user/chooseWorker";
    }

    @PostMapping("/admin/time_manager/choose")
    public String chooseUsers(@RequestParam(value = "myParam[]") List<String> ids) throws ParseException {
        //TODO достать -> запихнуть
        for (String id : ids) {
            UserEntity user = userService.getUser(userService.findUsernameByLogin(id).getId());
            var userw = new UserWorktimeEntity();
            userw.setId(userService.findUsernameByLogin(id).getId());
            userw.setStartTime(LocalTime.now());
            // userw = userWorktimeService.getUserWorkTime(Integer.parseInt(id));
            user.setUserWorktime(userw);
            userw.setUserok(user);
            userService.save(user);
        }

        return "redirect:/admin/time_manager";
    }


}
