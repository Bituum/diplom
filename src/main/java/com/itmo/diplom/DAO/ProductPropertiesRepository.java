package com.itmo.diplom.DAO;

import com.itmo.diplom.entities.ProductPropertiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertiesRepository extends JpaRepository<ProductPropertiesEntity, Integer> {
}
