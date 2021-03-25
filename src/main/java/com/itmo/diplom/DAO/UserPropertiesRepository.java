package com.itmo.diplom.DAO;

import com.itmo.diplom.entities.UserPropertiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPropertiesRepository extends JpaRepository<UserPropertiesEntity, Integer> {
}
