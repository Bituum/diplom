package com.itmo.diplom.controller;

import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.util.InitActiveDishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AOController {
    @Autowired
    private DishesServiceImpl dishesService;

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
    }

    @GetMapping("/active_orders")
    public String showAllActiveOrders(Model model){
        model.addAttribute("orderedDishes", dishesService.getAllDishesEntities());
        initActiveDishes(model);
        return "regular/special/activeOrders";
    }

    @PostMapping("/active_orders/clear/{id}")
    public String clearActiveDishes(@PathVariable("id") int id){
        DishesEntity dish = dishesService.getDishesEntity(id);
        dish.reduceOrderedCounter();

        if(dish.getOrdered() == 0){
            dish.setIsActive(false);
            dishesService.save(dish);
            if(dishesService.getAllDishesEntities().stream().allMatch(x -> x.getOrdered() == 0)){
                return "redirect:/";
            }
        }
        dishesService.save(dish);
        return "redirect:/active_orders";
    }
}
