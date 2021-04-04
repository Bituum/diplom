package com.itmo.diplom.repository;

import com.itmo.diplom.entity.OtherThingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherThingRepository extends JpaRepository<OtherThingEntity, Integer> {
}
