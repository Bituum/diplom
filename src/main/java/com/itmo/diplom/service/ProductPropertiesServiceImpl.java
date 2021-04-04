package com.itmo.diplom.service;

import com.itmo.diplom.repository.ProductPropertiesRepository;
import com.itmo.diplom.entity.ProductPropertiesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductPropertiesServiceImpl implements ProductPropertiesService{
    @Autowired
    private ProductPropertiesRepository productPropertiesRepository;

    @Override
    public List<ProductPropertiesEntity> getAllProductPropertiesEntities() {
        return productPropertiesRepository.findAll();
    }

    @Override
    public void saveOtherThingsEntities(ProductPropertiesEntity productProperties) {
        productPropertiesRepository.save(productProperties);
    }

    @Override
    public ProductPropertiesEntity getProductPropertiesEntity(int id) {
        ProductPropertiesEntity tmpProperty = null;
        Optional<ProductPropertiesEntity> optional = productPropertiesRepository.findById(id);
        if(optional.isPresent()){
            tmpProperty = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return tmpProperty;
    }

    @Override
    public void deleteProductPropertiesEntity(int id) {
        productPropertiesRepository.deleteById(id);
    }
}
