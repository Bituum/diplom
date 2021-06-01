package com.itmo.diplom.service;


import com.itmo.diplom.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUser();
    boolean save(UserEntity user);
    UserEntity getUser(int id);
    void deleteUser(int id);
}
