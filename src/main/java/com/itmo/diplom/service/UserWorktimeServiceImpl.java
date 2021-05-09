package com.itmo.diplom.service;

import com.itmo.diplom.entity.UserWorktimeEntity;
import com.itmo.diplom.repository.UserWorktimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWorktimeServiceImpl implements UserWorktimeService{

    @Autowired
    private UserWorktimeRepository userWorktimeRepository;

    @Override
    public List<UserWorktimeEntity> getAllUserWorkTime() {
        return userWorktimeRepository.findAll();
    }

    @Override
    public void save(UserWorktimeEntity userWorkTime) {
        userWorktimeRepository.save(userWorkTime);
    }

    @Override
    public UserWorktimeEntity getUserWorkTime(int id) {
        return null;
    }

    @Override
    public void deleteUserWorkTime(int id) {

    }
}
