package com.itmo.diplom.service;

import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.repository.DishesRepository;
import com.itmo.diplom.entity.DishesEntity;
import com.itmo.diplom.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@PropertySource("classpath:/orders.properties")
public class DishesServiceImpl implements DishesService{
    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsServiceImpl productsService;

    private static Logger logger = Logger.getLogger(DishesServiceImpl.class.getName());




    @Override
    public List<DishesEntity> getAllDishesEntities() {
        return dishesRepository.findAll();
    }

    @Override
    public void save(DishesEntity dish) {

        dishesRepository.save(dish);
    }

    @Override
    public DishesEntity getDishesEntity(int id) {
        return dishesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Dish not found")
        );
    }

    @Override
    public void deleteDishesEntity(int id) {
        dishesRepository.deleteById(id);
    }

    public boolean makeAnOrder(int dishId){
        Optional<DishesEntity>  optional = dishesRepository.findById(dishId);
        int amountOfProduct = 0;
        int counterCounterOrder = 0;
        DishesEntity dish;
        if(optional.isPresent()){
            dish = optional.get();
            List<ProductsEntity> list = dish.getProductsEntity();

            List<Integer> counterList = dishesRepository.findCounterOrder(dish.getId());
            for(ProductsEntity l : list){
                amountOfProduct = l.getProductProperties().getAmount();
                for(int i = 0; i < counterList.get(counterCounterOrder); i++){
                    //TODO: refact this garbage
                    //TODO 1st: remove the loop
                    //TODO 2nd: rafact this to java 8 this stream api and lambda
                    amountOfProduct--;
                    if(amountOfProduct == 0){
                        return false;
                    }
                }
                counterCounterOrder++;
            }
            dish.setIsActive(true);

            for(ProductsEntity l : list){
                l.getProductProperties().setAmount(amountOfProduct);
            }

            dishesRepository.save(dish);
            return true;
        }
        return false;
    }
}
