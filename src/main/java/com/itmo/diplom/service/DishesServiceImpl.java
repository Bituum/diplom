package com.itmo.diplom.service;

import com.itmo.diplom.DAO.DishesRepository;
import com.itmo.diplom.entities.DishesEntity;
import com.itmo.diplom.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        DishesEntity tmpDish = null;
        Optional<DishesEntity> optional = dishesRepository.findById(id);
        if(optional.isPresent()){
            tmpDish = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return tmpDish;
    }

    @Override
    public void deleteDishesEntity(int id) {
        dishesRepository.deleteById(id);
    }
}
