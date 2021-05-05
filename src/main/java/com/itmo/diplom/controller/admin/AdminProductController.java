package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.ProductPropertiesEntity;
import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.repository.ProductsRepository;
import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.service.ProductPropertiesServiceImpl;
import com.itmo.diplom.service.ProductsServiceImpl;
import com.itmo.diplom.util.InitActiveDishes;
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
public class AdminProductController {

    private static Logger logger = Logger.getLogger(AdminProductController.class.getName());

    @Autowired
    private ProductsServiceImpl productsService;

    @Autowired
    private ProductPropertiesServiceImpl propertiesService;

    @Autowired
    private DishesServiceImpl dishesService;

    @Autowired
    private ProductsRepository repository;

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
    }

    @GetMapping("admin/storage/all")
    public String showAllProduct(Model model){
        model.addAttribute("productsForm", productsService.getAllProductsEntities());
        initActiveDishes(model);
        return "admin/storage/showAll";
    }

    @GetMapping("/admin/storage/{id}")
    public String showProduct(Model model,
                              @PathVariable("id") int id
                              ){
        model.addAttribute("productForm", propertiesService.getProductPropertiesEntity(id));
        initActiveDishes(model);
        return "admin/storage/oneProduct";
    }

    @GetMapping("/admin/storage/create")
    public String createProduct(Model model){
        model.addAttribute("newProductForm", new ProductsEntity());
        initActiveDishes(model);
        return "admin/storage/createProduct";
    }

    @PostMapping("/admin/storage/create")
    public String createProduct(Model model,
                                @ModelAttribute("newProductForm") @Valid ProductsEntity product,
                                BindingResult result){
        if(result.hasErrors()){
            return "/admin/storage/createProduct";
        }
        productsService.save(product);
        return "redirect:/admin/storage/all";
    }

    @GetMapping("/admin/storage/edit/{id}")
    public String editProduct(@PathVariable("id") int id,
                              Model model){
        model.addAttribute("editProductForm", productsService.getProductsEntity(id));
        initActiveDishes(model);
        return "admin/storage/changeProduct";
    }

    @PostMapping("/admin/storage/edit/{id}")
    public String editProduct(@ModelAttribute("editProductForm") @Valid ProductsEntity product,
                              BindingResult result){
        if(result.hasErrors()){
            return "admin/storage/changeProduct";
        }
        productsService.save(product);
        return "redirect:/admin/storage/all";
    }

    @GetMapping("/admin/storage/{id}/properties")
    public String showProductProperties(Model model,
                                        @PathVariable("id") int id){
        try {
            model.addAttribute("productForm", propertiesService.getProductPropertiesEntity(id));
            initActiveDishes(model);
            logger.info("product property has been loaded                           [OK]");
            return "admin/storage/oneProduct";
        }catch (IllegalArgumentException empty){
            model.addAttribute("pid", id);
            model.addAttribute("editProductProperties", new ProductPropertiesEntity());
            initActiveDishes(model);
            return "admin/storage/editProductProperty";
        }
    }


    @GetMapping("/admin/storage/property/edit/{id}")
    public String updateProductProperties(Model model,
                                          @PathVariable("id") int id){
        model.addAttribute("pid", id);
        logger.info("id is ==   [" +id+"]");
        model.addAttribute("editProductProperties", propertiesService.getProductPropertiesEntity(id));
        initActiveDishes(model);
        return "admin/storage/editProductProperty";
    }

    @PostMapping("/admin/storage/property/edit/{id}")
    public String updateProductProperties(Model model,
                                          @ModelAttribute("editProductProperties") @Valid ProductPropertiesEntity product,
                                          BindingResult result,
                                          @PathVariable("id") int id){

        if(result.hasErrors()){
            return "admin/storage/editProductProperty";
        }
        logger.info("property id is ==   [" +product.getProductsId() +"]");
        logger.info("property name is ==   [" +product.getName() +"]");
        logger.info("property amount is ==   [" +product.getAmount() +"]");
        logger.info("property cost is ==   [" +product.getCost() +"]");
        if(!propertiesService.save(product)){
            logger.info("property havent been saven                    [NOT OK]");
        }
        logger.info("product                ["+ product.getProduct()+"]");
        return "redirect:/admin/storage/all";
    }

    @PostMapping("/admin/storage/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        repository.deleteById(id);
        logger.info("deleted                                             [OK]");
        return "redirect:/admin/storage/all";
    }

}
