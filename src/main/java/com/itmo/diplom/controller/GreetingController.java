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
    /*@GetMapping("/admin/menu")
    public String showAllMenu(Model model){
        model.addAttribute("dishesForm", dishesService.getAllDishesEntities());
        return "regular/greeting";
    }*/

/*    @GetMapping("/admin/menu/{id}")
    public String showDish(Model model,
                           @PathVariable("id") int id
    ){
        model.addAttribute("menuForm", dishesService.getDishesEntity(id));
        return "admin/menu/oneDish";
    }

    @GetMapping("/admin/menu/create")
    public String createDish(Model model,
                             @ModelAttribute("dishAttr") DishesEntity dishesEntity){
        List<Integer> listI = new ArrayList<>();
        List<String> listStr = new ArrayList<>();
        for(ProductsEntity p : productsService.getAllProductsEntities()){
            listI.add(p.getId());
            listStr.add(p.getProductDescription());
        }
        List<DishesEntity> listDish = new ArrayList<>();
        listDish = dishesService.getAllDishesEntities();

        model.addAttribute("lastId", listDish.get(listDish.size() - 1).getId());
        model.addAttribute("listOfProducts", listI);
        model.addAttribute("listStr", listStr);
        model.addAttribute("fullList", dishesService.getAllDishesEntities());
        return "admin/menu/createDish";
    }

    @PostMapping("/admin/menu/create")
    public String createDish(Model model,
                             @RequestParam(value = "id") String id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "time") String time,
                             @RequestParam(value = "myParam[]", required = false) List<String> idp,
                             @RequestParam(value = "myAmount[]", required = false) List<Integer> amount) throws ParseException {

        DishesEntity d = new DishesEntity();
        d.setId(Integer.getInteger(id));
        d.setNameOfDish(name);
        d.setTimeToCooking(timeChanger(time));
        dishesService.save(d);
        List<ProductsEntity> entityList = new ArrayList<>();
        for(int i = 0; i < idp.size(); i++){
            ProductsEntity tmp = productsService.getProductsEntity(productsRepository.findByProductDescription(idp.get(i)).get().getId());
            tmp.setCounterOrder(amount.get(i));
            entityList.add(tmp);
        }
        d.setProductsEntity(entityList);
        dishesService.save(d);
        //for(ProductsEntity p : dishesService.)

        //dishesService.save(dish);
        return "redirect:/admin/";
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

    @PostMapping("/admin/menu/delete/{id}")
    public String deleteUser(@PathVariable("id") int dishId){
        dishesService.deleteDishesEntity(dishId);
        return "redirect:/admin/";
    }*/
}
