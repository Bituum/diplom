package com.itmo.diplom.repository;

import com.itmo.diplom.entity.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends JpaRepository<DishesEntity, Integer> {
}
