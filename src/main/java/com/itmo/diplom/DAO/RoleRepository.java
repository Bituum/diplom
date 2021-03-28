package com.itmo.diplom.DAO;

import com.itmo.diplom.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
