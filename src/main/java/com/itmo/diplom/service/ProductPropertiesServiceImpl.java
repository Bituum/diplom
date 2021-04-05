package com.itmo.diplom.service;

import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.repository.ProductPropertiesRepository;
import com.itmo.diplom.entity.ProductPropertiesEntity;
import com.itmo.diplom.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductPropertiesServiceImpl implements ProductPropertiesService{
    @Autowired
    private ProductPropertiesRepository productPropertiesRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductPropertiesEntity> getAllProductPropertiesEntities() {
        return productPropertiesRepository.findAll();
    }

    @Override
    public void saveOtherThingsEntities(ProductPropertiesEntity productProperties) {
        Optional<ProductsEntity> product = productsRepository.findById(productProperties.getProductsId());
        if(product.isPresent()){
            ProductsEntity productsTmp = product.get();
            productsTmp.setProductProperties(productProperties);
            productProperties.setProduct(productsTmp);
        }else{
            throw new IllegalArgumentException("Could not save the product properties");
        }



        //productPropertiesRepository.save(productProperties);
    }

    @Override
    public ProductPropertiesEntity getProductPropertiesEntity(int id) {
        return productPropertiesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product properties not found")
        );
    }

    @Override
    public void deleteProductPropertiesEntity(int id) {
        productPropertiesRepository.deleteById(id);
    }
}
