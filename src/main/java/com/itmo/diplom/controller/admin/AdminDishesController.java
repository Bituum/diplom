package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.service.DishesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class AdminDishesController {

    private static Logger logger = Logger.getLogger(AdminDishesController.class.getName());

    @Autowired
    private DishesServiceImpl dishesService;

    @GetMapping("admin/")
    public String showAllDish(Model model){
        model.addAttribute("dishesForm", dishesService.getAllDishesEntities());
        return "admin/user/greeting";
    }

    @GetMapping("/admin/menu/{id}")
    public String showDish(Model model,
                              @PathVariable("id") int id
    ){
        model.addAttribute("menuForm", dishesService.getDishesEntity(id));
        return "admin/menu/oneDish";
    }

    @GetMapping("/admin/menu/create")
    public String createDish(Model model){
        model.addAttribute("newMenuForm", new DishesEntity());
        return "admin/menu/createDish";
    }

    @PostMapping("/admin/menu/create")
    public String createDish(Model model,
                                @ModelAttribute("newMenuForm") @Valid DishesEntity dish,
                                BindingResult result){
        if(result.hasErrors()){
            //model.addAttribute("errors", result);
        }
        dishesService.save(dish);
        return "redirect:/admin/menu/all";
    }

    @GetMapping("/admin/menu/edit/{id}")
    public String editDish(@PathVariable("id") int id,
                              Model model){
        model.addAttribute("editMenuForm", dishesService.getDishesEntity(id));
        return "admin/menu/changeMenu";
    }

    @PostMapping("/admin/menu/edit/{id}")
    public String editDish(Model model,
                              @ModelAttribute("editMenuForm") @Valid DishesEntity dish,
                              BindingResult result){
        if(result.hasErrors()){
            //model.addAttribute("errors", result);
            return "admin/menu/changeMenu";
        }
        dishesService.save(dish);
        return "redirect:/admin";
    }
}
