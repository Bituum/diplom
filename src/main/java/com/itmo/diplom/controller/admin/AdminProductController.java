package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.service.ProductPropertiesServiceImpl;
import com.itmo.diplom.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.Validation;

@Controller
public class AdminProductController {

    @Autowired
    private ProductsServiceImpl productsService;

    @Autowired
    private ProductPropertiesServiceImpl propertiesService;

    @GetMapping("admin/storage/all")
    public String showAllProduct(Model model){
        model.addAttribute("productsForm", productsService.getAllProductsEntities());
        return "/admin/storage/showAll";
    }

    @GetMapping("/admin/storage/{id}")
    public String showProduct(Model model,
                              @PathVariable("id") int id
                              ){
        model.addAttribute("productForm", productsService.getProductsEntity(id));
        return "admin/storage/oneProduct";
    }

    @GetMapping("/admin/storage/create")
    public String createProduct(Model model){
        model.addAttribute("newProductForm", new ProductsEntity());
        return "admin/storage/createProduct";
    }

    @PostMapping
    public String createProduct(Model model,
                                @ModelAttribute("newModelForm") @Valid ProductsEntity product,
                                BindingResult result){

        //TODO: creation of that model
        return "redirect:/admin/storage/all";
    }

    @GetMapping("/admin/storage/edit/{id}")
    public String editProduct(@PathVariable("id") int id,
                              Model model){
        model.addAttribute("editProductForm", productsService.getProductsEntity(id));
        return "admin/storage/changeProduct";
    }

    @PostMapping("/admin/storage/edit/{id}")
    public String editProduct(Model model,
                              @ModelAttribute("editProductForm") @Valid ProductsEntity product,
                              BindingResult result){
        //TODO: creation of that model
        return "redirect:/admin/storage/all";
    }

    @GetMapping("/admin/storage/property/{id}")
    public String showProductProperties(Model model,
                                        @PathVariable("id") int id){
        model.addAttribute("productProperty", propertiesService.getProductPropertiesEntity(id));
        return "/admin/storage/showAllProductProperties";
    }

    @GetMapping("/admin/storage/property/edit/{id}")
    public String updateProductProperties(Model model,
                                          @PathVariable("id") int id){
        model.addAttribute("editProductProperties", propertiesService.getProductPropertiesEntity(id));
        return "/admin/storage/editProductProperty";
    }

    @PostMapping("/admin/storage/property/edit/{id}")
    public String updateProductProperties(Model model,
                                          @ModelAttribute("editProductForm") @Valid ProductsEntity product,
                                          BindingResult result){

        return "redirect:/admin/storage/all";
    }
}
