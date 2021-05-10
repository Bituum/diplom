package com.itmo.diplom.controller;

import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.service.ProductsServiceImpl;
import com.itmo.diplom.util.InitActiveDishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class NPController {
    @Autowired
    private ProductsServiceImpl productsService;

    @Autowired
    private DishesServiceImpl dishesService;

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
    }

    @GetMapping("/few_products")
    public String showAllNoProducts(Model model){
        List<ProductsEntity> list = productsService.findProductWithFewAmount(productsService.getAllProductsEntities());
        model.addAttribute("productsForm", productsService.getSpecificProducts(list.stream()
                .filter(Objects::nonNull)
                .map(x -> x.getProductProperties().getProductsId())
                .collect(Collectors.toList())));
        initActiveDishes(model);
        return "admin/special/noProducts";
    }
}
