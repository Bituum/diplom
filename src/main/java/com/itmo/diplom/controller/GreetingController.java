package com.itmo.diplom.controller;


import com.itmo.diplom.controller.admin.AdminDishesController;
import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.repository.ProductsRepository;
import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.service.ProductsServiceImpl;
import com.itmo.diplom.util.InitActiveDishes;
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

@Controller
@RequestMapping("/user")
public class GreetingController {
    private static Logger logger = Logger.getLogger(AdminDishesController.class.getName());

    @Autowired
    private DishesServiceImpl dishesService;

    @Autowired
    private ProductsServiceImpl productsService;

    @Autowired
    private ProductsRepository productsRepository;

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
    }

    @GetMapping("/")
    public String showAllDish(Model model){
        model.addAttribute("dishesForm", dishesService.getAllDishesEntities());
        initActiveDishes(model);
        return "/regular/greeting";
    }

    @PostMapping("/make_order/{id}")
    public String makeOrder(Model model,
                            @PathVariable("id") int id
    )
    {
        if (!dishesService.makeAnOrder(id)) {
            model.addAttribute("dishesForm", dishesService.getAllDishesEntities());
            model.addAttribute("amountError", 1);
            initActiveDishes(model);
            return "regular/greeting";
        }
        return "redirect:/user/";
    }

}
