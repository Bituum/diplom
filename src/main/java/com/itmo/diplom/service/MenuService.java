package com.itmo.diplom.service;


import com.itmo.diplom.entity.MenuEntity;

import java.util.List;


public interface MenuService {
    List<MenuEntity> getAllMenuEntities();
    void saveMenuEntity(MenuEntity menu);
    MenuEntity getMenuEntity(int id);
    void deleteMenuEntity(int id);
}
