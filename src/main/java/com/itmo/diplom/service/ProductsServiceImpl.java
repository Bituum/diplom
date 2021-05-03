package com.itmo.diplom.service;

import com.itmo.diplom.entity.ProductPropertiesEntity;
import com.itmo.diplom.repository.ProductsRepository;
import com.itmo.diplom.entity.ProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

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

    public List<ProductsEntity> findProductWithFewAmount(List<ProductsEntity> list){
        return list
                .stream().filter(Objects::nonNull)
                .map((entry) -> {
                    ProductPropertiesEntity  properties = entry.getProductProperties();
                    if(properties.getAmount() <= 80){
                        return entry;
                    }

                    return null;
                })
                .collect(Collectors.toList());

    }

    public List<ProductsEntity> getSpecificProducts(List<Integer> ids){
        return productsRepository.findAllById(ids);
    }
}
