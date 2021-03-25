package com.itmo.diplom.service;

import com.itmo.diplom.entities.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUserEntities();
    void saveMenuEntity(UserEntity user);
    UserEntity getUserEntity(int id);
    void deleteUserEntity(int id);
}
