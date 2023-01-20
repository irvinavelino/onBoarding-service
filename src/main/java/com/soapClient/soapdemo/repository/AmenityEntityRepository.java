package com.soapClient.soapdemo.repository;

import com.soapClient.soapdemo.entity.AmenityEntity;
import com.soapClient.soapdemo.entity.HotelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AmenityEntityRepository extends CrudRepository<AmenityEntity,Integer> {
    public boolean existsAmenityByname(String name);
    public AmenityEntity getByname(String name);
    public AmenityEntity findAmenityByName(String name);
    public List<HotelEntity> findHotelsByAmenityId(int amenityId);
    public List<AmenityEntity> findAll();
}
