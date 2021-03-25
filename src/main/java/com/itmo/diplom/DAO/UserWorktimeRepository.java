package com.itmo.diplom.DAO;

import com.itmo.diplom.entities.UserWorktimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorktimeRepository extends JpaRepository<UserWorktimeEntity, Integer> {
}
