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
        return userWorktimeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("not found")
        );
    }

    @Override
    public void deleteUserWorkTime(int id) {
        userWorktimeRepository.deleteById(id);
    }

    public void deleteAllUserWorkTime(){
        userWorktimeRepository.deleteAll();
    }
}
