package com.itmo.diplom.service;

import com.itmo.diplom.entity.UserEntity;
import com.itmo.diplom.entity.UserPropertiesEntity;
import com.itmo.diplom.repository.UserPropertiesRepository;
import com.itmo.diplom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPropertiesServiceImpl implements UserPropertiesService{
    @Autowired
    private UserPropertiesRepository userPropertiesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserPropertiesEntity> getAllProperties() {
        return userPropertiesRepository.findAll();
    }

    @Override
    public boolean save(UserPropertiesEntity userProperties) {
        UserEntity tmpUser = userRepository.findById(userProperties.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("Not found"));
        //TODO try catch in controllers
            tmpUser.setUserPropertiesEntity(userProperties);
            userProperties.setUser(tmpUser);
            userRepository.save(tmpUser);
            return true;
    }

    @Override
    //TODO in controllers try catch

    public UserPropertiesEntity getUserProperty(int id) {
        return userPropertiesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not found")
        );
    }

    @Override
    public void deleteUserProperty(int id) {
        userPropertiesRepository.deleteById(id);
    }
}
