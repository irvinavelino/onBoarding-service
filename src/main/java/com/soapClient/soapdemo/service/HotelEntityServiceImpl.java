package com.soapClient.soapdemo.service;

import com.soapClient.soapdemo.entity.HotelEntity;
import com.soapClient.soapdemo.repository.HotelEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class HotelEntityServiceImpl implements HotelEntityService
{
    private HotelEntityRepository repository;
    public HotelEntityServiceImpl() {

    }

    @Autowired
    public HotelEntityServiceImpl(HotelEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public HotelEntity getEntityByHotelId(int id)
    {
        try {
            return this.repository.findByHotelId(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<HotelEntity> filterEntityByName(String name) {
        try {
            List<HotelEntity> list = new ArrayList<>();
            Pageable paging=PageRequest.of(0,5);
            repository.findByName(name).forEach(e -> list.add(e));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List< HotelEntity > getAllEntities() {
        try {
            List<HotelEntity> list = new ArrayList<>();
            Pageable paging=PageRequest.of(0,5);
            repository.findAll().forEach(e -> list.add(e));
            return list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HotelEntity addEntity(HotelEntity entity) {
        try {
            return this.repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean updateEntity(HotelEntity entity) {
        try {
            this.repository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteEntityByHotelId(int id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
