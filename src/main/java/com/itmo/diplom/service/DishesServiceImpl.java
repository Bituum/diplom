package com.itmo.diplom.service;

import com.itmo.diplom.repository.DishesRepository;
import com.itmo.diplom.entity.DishesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishesServiceImpl implements DishesService{
    @Autowired
    private DishesRepository dishesRepository;

    @Override
    public List<DishesEntity> getAllDishesEntities() {
        return dishesRepository.findAll();
    }

    @Override
    public void saveDishesEntity(DishesEntity dish) {
        dishesRepository.save(dish);
    }

    @Override
    public DishesEntity getDishesEntity(int id) {
        return dishesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not found")
        );
    }

    @Override
    public void deleteDishesEntity(int id) {
        dishesRepository.deleteById(id);
    }
}
