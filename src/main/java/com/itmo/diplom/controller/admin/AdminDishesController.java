package com.itmo.diplom.controller.admin;

import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.repository.DishesRepository;
import com.itmo.diplom.repository.ProductsRepository;
import com.itmo.diplom.service.DishesServiceImpl;
import com.itmo.diplom.service.ProductPropertiesServiceImpl;
import com.itmo.diplom.service.ProductsServiceImpl;
import com.itmo.diplom.util.InitActiveDishes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class AdminDishesController {

    private static Logger logger = Logger.getLogger(AdminDishesController.class.getName());

    private boolean hasError;

    @Autowired
    private DishesServiceImpl dishesService;

    @Autowired
    private ProductsServiceImpl productsService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private DishesRepository repository;

    @Autowired
    private ProductPropertiesServiceImpl propertiesService;

    private void initActiveDishes(Model model){
        new InitActiveDishes().setDishesService(dishesService);
        model.addAttribute("activeDish", InitActiveDishes.initActiveButton());
        logger.info("Count of active dishes = " + dishesService.getAllDishesEntities().stream().map(DishesEntity::isActive).collect(Collectors.toList()));
    }

    private LocalTime timeChanger(String str) throws ParseException {
        return LocalTime.parse(str);
    }

    @GetMapping("/admin/")
    public String showAllDish(Model model) {
        model.addAttribute("dishesForm", dishesService.getAllDishesEntities());
        initActiveDishes(model);
        return "admin/user/greeting";
    }


    @GetMapping("/admin/menu/{id}")
    public String showDish(Model model,
                           @PathVariable("id") int id
    ) {
        model.addAttribute("menuForm", dishesService.getDishesEntity(id));
        return "admin/menu/oneDish";
    }

    @GetMapping("/admin/menu/create")
    public String createDish(Model model,
                             @ModelAttribute("dishAttr") DishesEntity dishesEntity) {
        List<Integer> listI = new ArrayList<>();
        List<String> listStr = new ArrayList<>();
        for (ProductsEntity p : productsService.getAllProductsEntities()) {
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
                             @RequestParam(value = "name")
                             @Size(min = 1, max = 50, message = "Название продукта должно быть больше 1 и меньше 50") String name,
                             @RequestParam(value = "time") String time,
                             @RequestParam(value = "myParam[]", required = false) List<String> idp,
                             @RequestParam(value = "myAmount[]", required = false) List<Integer> amount) throws ParseException {

        DishesEntity d = new DishesEntity();
        d.setId(Integer.getInteger(id));
        d.setNameOfDish(name);
        d.setTimeToCooking(timeChanger(time));
        dishesService.save(d);
        List<ProductsEntity> entityList = new ArrayList<>();
        for (int i = 0; i < idp.size(); i++) {
            ProductsEntity tmp = productsService.getProductsEntity(productsRepository.findByProductDescription(idp.get(i)).get().getId());
            entityList.add(tmp);
        }
        d.setProductsEntity(entityList);
        dishesService.save(d);
        for (int i = 0; i < amount.size(); i++) {
            repository.insertCounterOrder(amount.get(i), d.getId());
            logger.info("amount is " + amount.get(i));
            logger.info("dish id is " + d.getId());
            logger.info("INSERT HAS BEEN INSERTED");
        }
        return "redirect:/admin/";
    }

    @GetMapping("/admin/menu/edit/{id}")
    public String editDish(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("editMenuForm", dishesService.getDishesEntity(id));
        return "admin/menu/changeMenu";
    }

    @PostMapping("/admin/menu/edit/{id}")
    public String editDish(Model model,
                           @ModelAttribute("editMenuForm") @Valid DishesEntity dish,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "admin/menu/changeMenu";
        }
        dishesService.save(dish);
        return "redirect:/admin/";
    }


    @PostMapping("/admin/make_order/{id}")
    public String makeOrder(Model model,
                            @PathVariable("id") int id
    )
    {
        if (!dishesService.makeAnOrder(id)) {
            model.addAttribute("dishesForm", dishesService.getAllDishesEntities());
            model.addAttribute("amountError", 1);
            return "admin/user/greeting";
        }

        return "redirect:/admin/";
    }


    @PostMapping("/admin/menu/delete/{id}")
    public String deleteDish(@PathVariable("id") int dishId) {
        dishesService.deleteDishesEntity(dishId);
        return "redirect:/admin/";
    }

    //TODO чтобы сделать вывод всех описаний продукта у которых есть проблема, сделай через thymeleaf if property.amount == 0
}
