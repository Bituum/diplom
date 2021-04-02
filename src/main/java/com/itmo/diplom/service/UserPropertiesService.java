package com.itmo.diplom.service;

import com.itmo.diplom.entities.UserEntity;
import com.itmo.diplom.entities.UserPropertiesEntity;

import java.util.List;

public interface UserPropertiesService {
    List<UserPropertiesEntity> getAllProperties();
    boolean save(UserPropertiesEntity userProperties);
    UserPropertiesEntity getUserProperty(int id);
    void deleteUserProperty(int id);
}
