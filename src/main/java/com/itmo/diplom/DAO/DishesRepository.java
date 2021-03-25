package com.itmo.diplom.DAO;

import com.itmo.diplom.entities.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends JpaRepository<DishesEntity, Integer> {
}
