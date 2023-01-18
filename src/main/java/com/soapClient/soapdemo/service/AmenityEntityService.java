package com.soapClient.soapdemo.service;

import com.soapClient.soapdemo.entity.AmenityEntity;

public interface AmenityEntityService {
    public AmenityEntity addEntity(AmenityEntity entity);
    public AmenityEntity AmenityNameExists(String name);

}
