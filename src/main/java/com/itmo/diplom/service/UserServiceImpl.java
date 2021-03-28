package com.itmo.diplom.service;

import com.itmo.diplom.DAO.UserRepository;
import com.itmo.diplom.entities.OtherThingEntity;
import com.itmo.diplom.entities.Role;
import com.itmo.diplom.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(UserEntity user) {
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<UserEntity> userfromBD = userRepository.findByLogin(user.getUsername());
        UserEntity tmpUser = null;
        if (userfromBD.isPresent()) {
            tmpUser = userfromBD.get();
            tmpUser.setRoles(Collections.singleton(new Role(1, "USER")));
            tmpUser.setPasswd(cryptPasswordEncoder.encode(tmpUser.getPassword()));
        }else{
            System.out.println("NULL user");
            throw new IllegalArgumentException();
        }
        userRepository.save(tmpUser);
        return true;
    }

    @Override
    public UserEntity getUser(int id) {
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
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public UserEntity loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(name).orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return user;
    }

}
