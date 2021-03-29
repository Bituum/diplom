package com.itmo.diplom.service;

import com.itmo.diplom.DAO.RoleRepository;
import com.itmo.diplom.DAO.UserRepository;
import com.itmo.diplom.entities.OtherThingEntity;
import com.itmo.diplom.entities.Role;

import com.itmo.diplom.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(UserEntity user) {
//        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
//        Optional<UserEntity> userfromBD = userRepository.findByLogin(user.getUsername());
//        UserEntity tmpUser = null;
//        System.out.println(userfromBD.toString());
//        if (userfromBD.isEmpty()){
//            tmpUser = user;
//            tmpUser.setRoles(Collections.singleton(new Role(1, "USER")));
//            tmpUser.setPasswd(bCryptPasswordEncoder.encode(tmpUser.getPassword()));
//            userRepository.save(tmpUser);
//            return true;
//        }
//        else{
//            System.out.println("This user is already created");
//            throw new IllegalArgumentException();
//        }
        user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.getOne(1));
        user.setRoles(roleSet);
        userRepository.save(user);
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

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return userRepository.findByLogin(s).orElseThrow(() -> new UsernameNotFoundException("User not present"));
//    }

//    @Override
//    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not present"));
//    }



}
