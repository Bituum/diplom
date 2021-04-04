package com.itmo.diplom.service;

import com.itmo.diplom.repository.MenuRepository;
import com.itmo.diplom.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuEntity> getAllMenuEntities() {
        return menuRepository.findAll();
    }

    @Override
    public void saveMenuEntity(MenuEntity menu) {
        menuRepository.save(menu);
    }

    @Override
    public MenuEntity getMenuEntity(int id) {
        MenuEntity tmpEntity = null;
        Optional<MenuEntity> optional = menuRepository.findById(id);
        if(optional.isPresent()){
            tmpEntity = optional.get();
        }else {
            System.out.println("!!!!!Optional is empty!!!!!");
            throw new IllegalArgumentException();
        }
        return tmpEntity;
    }

    @Override
    public void deleteMenuEntity(int id) {
        menuRepository.deleteById(id);
    }
}
