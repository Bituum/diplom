package com.itmo.diplom.service;

import com.itmo.diplom.entity.DishesEntity;


import java.util.List;

public interface DishesService {
    List<DishesEntity> getAllDishesEntities();
    void saveDishesEntity(DishesEntity dish);
    DishesEntity getDishesEntity(int id);
    void deleteDishesEntity(int id);
}
