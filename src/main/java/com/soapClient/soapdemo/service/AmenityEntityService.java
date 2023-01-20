package com.soapClient.soapdemo.service;

import com.soapClient.soapdemo.entity.AmenityEntity;
import com.soapClient.soapdemo.entity.HotelEntity;

public interface AmenityEntityService {
    public AmenityEntity addEntity(AmenityEntity entity);
    public AmenityEntity AmenityNameExists(String name);
    public AmenityEntity findEntityByName(String name);
    public boolean updateEntity(AmenityEntity entity);
}
