package com.itmo.diplom.util;

import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.service.DishesService;
import com.itmo.diplom.service.DishesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Component
public class InitActiveDishes {
    private static DishesServiceImpl dishesService;
    private static final Map<Integer, List<DishesEntity>> activeDishes = new HashMap<>();

    @Autowired
    public void setDishesService(DishesServiceImpl service){
        dishesService = service;
    }

    public static int initActiveButton(){

        List<DishesEntity> dishesEntities = dishesService.getAllDishesEntities();
        List<DishesEntity> selectedDishes = new ArrayList<>();
        for(DishesEntity d : dishesEntities){
            if(d.isActive()){
                selectedDishes.add(d);
            }
        }
        /*activeDishes.put(counter, selectedDishes);*/
        return selectedDishes.size();
    }

}
