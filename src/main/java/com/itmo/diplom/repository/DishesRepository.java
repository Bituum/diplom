package com.itmo.diplom.repository;

import com.itmo.diplom.entity.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DishesRepository extends JpaRepository<DishesEntity, Integer> {
    @Query(value = "select CounterOrder from dishes d join product_dishes pd on d.id = pd.dish_id where d.id = :v", nativeQuery = true)
    List<Integer> findCounterOrder(@Param("v") int id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update product_dishes join dishes d on d.id = product_dishes.dish_id set product_dishes.CounterOrder = :vs  where dish_id = :v")
    void insertCounterOrder(@Param("vs") int productId, @Param("v") int dishesId);
}
