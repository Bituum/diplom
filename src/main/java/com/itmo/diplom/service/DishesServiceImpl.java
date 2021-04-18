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
        DishesEntity dish;
        if(optional.isPresent()){
            dish = optional.get();
            dish.initActive();
            List<ProductsEntity> list = dish.getProductsEntity();
            for(ProductsEntity l : list){
                List<Integer> counterList = dishesRepository.findCounterOrder(l.getId());
                for(int i = 0; i < counterList.size(); i++){
                    amountOfProduct = l.getProductProperties().getAmount();
                    if(amountOfProduct < 20){
                        logger.info("less than 20 products exception");
                    }
                    amountOfProduct--;
                    if(amountOfProduct == 0){
                        l.setCounterOrder(0);
                        throw new IllegalArgumentException("zero product");
                    }

                }
            }

            for(ProductsEntity l : list){
                l.getProductProperties().setAmount(amountOfProduct);
            }
            dishesRepository.save(dish);
        }
        return false;
    }
}
