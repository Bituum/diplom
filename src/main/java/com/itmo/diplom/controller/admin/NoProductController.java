package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.service.ProductsServiceImpl;
import com.sun.mail.util.LineInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class NoProductController {

    @Autowired
    private ProductsServiceImpl productsService;

    @GetMapping("/admin/few_products")
    public String showAllNoProduct(Model model){
        List<ProductsEntity> list = productsService.findProductWithFewAmount(productsService.getAllProductsEntities());
        model.addAttribute("productsForm", productsService.getSpecificProducts(list.stream()
                .filter(Objects::nonNull)
                .map(x -> x.getProductProperties().getProductsId())
                .collect(Collectors.toList())));
        return "admin/special/noProducts";
    }
}
