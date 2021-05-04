package com.itmo.diplom.service;

import com.itmo.diplom.entity.UserEntity;
import com.itmo.diplom.entity.UserWorktimeEntity;

import java.util.List;

public interface UserWorktimeService {
    List<UserWorktimeEntity> getAllUserWorkTime();
    boolean save(UserWorktimeEntity userWorkTime);
    UserWorktimeEntity getUserWorkTime(int id);
    void deleteUserWorkTime(int id);
}
