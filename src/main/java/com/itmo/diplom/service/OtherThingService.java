package com.itmo.diplom.service;

import com.itmo.diplom.entity.OtherThingEntity;

import java.util.*;

public interface OtherThingService {
    List<OtherThingEntity> getAllOtherThingEntities();
    void saveOtherThingsEntities(OtherThingEntity otherThingEntity);
    OtherThingEntity getOtherThingEntity(int id);
    void deleteOtherThingEntity(int id);
}
