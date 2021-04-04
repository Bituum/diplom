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
        UserEntity tmpUser = new UserEntity();
        Optional<UserEntity> user = userRepository.findById(userProperties.getUserId());
        //находим юзера у юзеррепозитория

        if(user.isPresent()){
            tmpUser = user.get();
            //достаём
            tmpUser.setUserPropertiesEntity(userProperties);
            //ставим у юзера его проперти
            userProperties.setUser(tmpUser);
            //ставим у проперти юзера иначе ексепшн

            userPropertiesRepository.save(userProperties);
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }

        return true;
    }

    @Override
    public UserPropertiesEntity getUserProperty(int id) {
        UserPropertiesEntity tmpUserProperties = null;
        Optional<UserPropertiesEntity> optional = userPropertiesRepository.findById(id);
        if(optional.isPresent()){
            tmpUserProperties = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return tmpUserProperties;
    }

    @Override
    public void deleteUserProperty(int id) {
        userPropertiesRepository.deleteById(id);
    }
}
