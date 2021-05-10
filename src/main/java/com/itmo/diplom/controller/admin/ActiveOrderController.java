package com.itmo.diplom.controller.admin;

import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.util.InitActiveDishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class ActiveOrderController {

    @Autowired
    private DishesServiceImpl dishesService;

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
    }

    @GetMapping("/active_orders")
    public String showAllActiveOrders(Model model){



        initActiveDishes(model);
        return "/admin/special/activeOrders";
    }
}
