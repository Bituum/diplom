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
        List<ProductsEntity> products = dish.getProductsEntity();
        logger.info("products : " + dish.getProductsEntity());
        List<ProductsEntity> checked = new ArrayList<>();
        System.out.println(products);
        for(ProductsEntity p : products){
            logger.info("product description is  " + p.getProductDescription());
            //findByProductDecription is custom Jpa query
            Optional<ProductsEntity> tmp = productsRepository.findByProductDescription(p.getProductDescription());
            if(tmp.isPresent()){
                ProductsEntity productsEntity = tmp.get();
                checked.add(productsEntity);
            }
        }
        dish.setProductsEntity(checked);
        logger.info("dishes product now: " + dish.getProductsEntity());
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

    public void makeAnOrder(int dishId){
        Optional<DishesEntity>  optional = dishesRepository.findById(dishId);;
        int amountOfProduct = 0;
        DishesEntity dish;
        if(optional.isPresent()){
            dish = optional.get();
            List<ProductsEntity> list = dish.getProductsEntity();
            for(ProductsEntity l : list){
                for(int i = 0; i < l.getCounterOrder(); i++){
                    amountOfProduct = l.getProductProperties().getAmount();
                    amountOfProduct--;
                    if(amountOfProduct < 20){
                        logger.info("less than 20 products exception");
                        throw new IllegalArgumentException("few products");
                    }
                }
            }

            for(ProductsEntity l : list){
                l.getProductProperties().setAmount(amountOfProduct);
            }
            dishesRepository.save(dish);
        }
    }
}
