package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.util.InitActiveDishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
public class AdminActiveOrderController {

    @Autowired
    private DishesServiceImpl dishesService;

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
    }

    @GetMapping("admin/active_orders")
    public String showAllActiveOrders(Model model){
        model.addAttribute("orderedDishes", dishesService.getAllDishesEntities());
        initActiveDishes(model);
        return "/admin/special/activeOrders";
    }

    @PostMapping("admin/active_orders/clear/{id}")
    public String clearActiveDish(@PathVariable("id") int id){
        DishesEntity dish = dishesService.getDishesEntity(id);
        dish.reduceOrderedCounter();

        if(dish.getOrdered() == 0){
            dish.setIsActive(false);
            dishesService.save(dish);
            if(dishesService.getAllDishesEntities().stream().allMatch(x -> x.getOrdered() == 0)){
                return "redirect:/admin/";
            }
        }
        dishesService.save(dish);
        return "redirect:/admin/active_orders";
    }
}
