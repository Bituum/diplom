package com.itmo.diplom.service;

import com.itmo.diplom.DAO.UserRepository;
import com.itmo.diplom.entities.OtherThingEntity;
import com.itmo.diplom.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUserEntities() {
        return userRepository.findAll();
    }

    @Override
    public void saveMenuEntity(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity getUserEntity(int id) {
        UserEntity tmpUser = null;
        Optional<UserEntity> optional = userRepository.findById(id);
        if(optional.isPresent()){
            tmpUser = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return tmpUser;
    }

    @Override
    public void deleteUserEntity(int id) {
        userRepository.deleteById(id);
    }
}
