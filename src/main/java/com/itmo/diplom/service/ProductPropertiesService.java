package com.itmo.diplom.service;

import com.itmo.diplom.entities.OtherThingEntity;
import com.itmo.diplom.entities.ProductPropertiesEntity;

import java.util.List;

public interface ProductPropertiesService {
    List<ProductPropertiesEntity> getAllProductPropertiesEntities();
    void saveOtherThingsEntities(ProductPropertiesEntity productProperties);
    ProductPropertiesEntity getProductPropertiesEntity(int id);
    void deleteProductPropertiesEntity(int id);
}
