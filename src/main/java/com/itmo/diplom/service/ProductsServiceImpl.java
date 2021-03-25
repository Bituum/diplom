package com.itmo.diplom.service;

import com.itmo.diplom.DAO.ProductsRepository;
import com.itmo.diplom.entities.ProductPropertiesEntity;
import com.itmo.diplom.entities.ProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductsServiceImpl implements ProductsService{
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductsEntity> getAllProductsEntities() {
        return productsRepository.findAll();
    }

    @Override
    public void saveStorageEntity(ProductsEntity product) {
        productsRepository.save(product);
    }

    @Override
    public ProductsEntity getProductsEntity(int id) {
        ProductsEntity tmpProduct = null;
        Optional<ProductsEntity> optional = productsRepository.findById(id);
        if(optional.isPresent()){
            tmpProduct = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return tmpProduct;
    }

    @Override
    public void deleteProductsEntity(int id) {
        productsRepository.deleteById(id);
    }
}
