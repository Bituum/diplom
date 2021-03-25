package com.itmo.diplom.DAO;

import com.itmo.diplom.entities.OtherThingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
@Repository
public interface OtherThingRepository extends JpaRepository<OtherThingEntity, Integer> {
}
