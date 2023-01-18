package com.soapClient.soapdemo.repository;
import com.soapClient.soapdemo.entity.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelEntityRepository extends CrudRepository<HotelEntity,Integer>{
    public List<HotelEntity> findByName(String name);
    public HotelEntity findByHotelId(int id);
    List<HotelEntity> findAll();
}
