package com.soapClient.soapdemo.service;

import com.soapClient.soapdemo.entity.HotelEntity;

import java.util.List;

public interface HotelEntityService
{
    public HotelEntity getEntityByHotelId(int id);
    public List<HotelEntity> filterEntityByName(String name);
    public List<HotelEntity> getAllEntities();
    public HotelEntity addEntity(HotelEntity entity);
    public boolean updateEntity(HotelEntity entity);
    public boolean deleteEntityByHotelId(int id);
}
