package com.itmo.diplom.service;

import com.itmo.diplom.repository.ProductsRepository;
import com.itmo.diplom.entity.ProductsEntity;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductsServiceImpl implements ProductsService{
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductsEntity> getAllProductsEntities() {
        return productsRepository.findAll();
    }

    @Override
    public void save(ProductsEntity product) {
        productsRepository.save(product);
    }

    @Override
    public ProductsEntity getProductsEntity(int id) {
        return productsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found")
        );
    }

    @Override
    public void deleteProductsEntity(int id) {
        productsRepository.deleteById(id);
    }
}
