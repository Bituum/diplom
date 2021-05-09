package com.itmo.diplom.repository;

import com.itmo.diplom.entity.UserWorktimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorktimeRepository extends JpaRepository<UserWorktimeEntity, Integer> {

}
