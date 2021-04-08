package com.itmo.diplom.repository;

import com.itmo.diplom.entity.ProductsEntity;
import com.itmo.diplom.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer> {
    Optional<ProductsEntity> findByProductDescription(String str);
}
