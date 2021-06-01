package com.itmo.diplom.service;

import com.itmo.diplom.controller.admin.AdminDishesController;
import com.itmo.diplom.repository.RoleRepository;
import com.itmo.diplom.repository.UserRepository;
import com.itmo.diplom.entity.Role;

import com.itmo.diplom.entity.UserEntity;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(UserEntity user) {
        if (checkLogin(user.getLogin())) {
            throw new IllegalArgumentException("Username is already exist");
        }
        user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.getOne(1));
        user.setRoles(roleSet);
        userRepository.save(user);
        return true;
    }

    public void forceSave(UserEntity user) {
        user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.getOne(1));
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    @Override
    public UserEntity getUser(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product not found")
        );
    }


    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public UserEntity findUsernameByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(
                IllegalArgumentException::new
        );
    }

    public boolean checkLogin(String login) {
        Optional<UserEntity> user = userRepository.findByLogin(login);
        return user.isPresent();
    }
}
