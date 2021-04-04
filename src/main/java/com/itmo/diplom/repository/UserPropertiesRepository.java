package com.itmo.diplom.repository;

import com.itmo.diplom.entity.UserPropertiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPropertiesRepository extends JpaRepository<UserPropertiesEntity, Integer> {
}
