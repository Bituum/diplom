package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

@Controller
public class AdminDishesController {

    private static Logger logger = Logger.getLogger(AdminDishesController.class.getName());

    @Autowired
    private DishesServiceImpl dishesService;

    @Autowired
    private ProductsServiceImpl productsService;

    @InitBinder
    public void dataBinding(WebDataBinder binder){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        binder.registerCustomEditor(Time.class, new CustomDateEditor(format, false));
    }

    @ModelAttribute("dishAttr")
    public DishesEntity getDishes(){
        DishesEntity dish = new DishesEntity();
        dish.setProductsEntity(new ArrayList<>());
        return dish;
    }

    @GetMapping("/admin/")
    public String showAllDish(Model model){
        model.addAttribute("dishesForm", dishesService.getAllDishesEntities());
        return "admin/user/greeting";
    }
    @GetMapping("/admin/menu")
    public String showAllMenu(Model model){
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
    public String createDish(Model model,
                             @ModelAttribute("dishAttr") DishesEntity dishesEntity){
        model.addAttribute("listOfProducts", productsService.getAllProductsEntities());
        return "admin/menu/createDish";
    }

    @PostMapping("/admin/menu/create")
    public String createDish(Model model,
                                @ModelAttribute("newMenuForm") @Valid DishesEntity dish,
                                BindingResult result){
        if(result.hasErrors()){
            //model.addAttribute("errors", result);
        }
        logger.info("name of dish == ["+ dish.getNameOfDish()+ "] dish TFC == [" + dish.getTimeToCooking()+"]");
        logger.info("products == ["+ dish.getProductsEntity()+ "]");
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

    @PostMapping("/admin/make_order/{id}")
    public String makeOrder(Model model,
                            @PathVariable("id") int id){
        dishesService.makeAnOrder(id);
        return "redirect:/admin";
    }
}
