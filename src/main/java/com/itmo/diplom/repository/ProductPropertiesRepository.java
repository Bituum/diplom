package com.itmo.diplom.repository;

import com.itmo.diplom.entity.ProductPropertiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertiesRepository extends JpaRepository<ProductPropertiesEntity, Integer> {
}
