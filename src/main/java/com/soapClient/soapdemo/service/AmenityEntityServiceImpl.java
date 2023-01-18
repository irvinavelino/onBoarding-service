package com.soapClient.soapdemo.service;

import com.soapClient.soapdemo.entity.AmenityEntity;
import com.soapClient.soapdemo.repository.AmenityEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityEntityServiceImpl implements AmenityEntityService{
    private AmenityEntityRepository repository;

    public AmenityEntityServiceImpl() {
    }
    @Autowired
    public AmenityEntityServiceImpl(AmenityEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public AmenityEntity addEntity(AmenityEntity entity) {
        try {
            return this.repository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AmenityEntity AmenityNameExists(String name) {
        try{
            if(!this.repository.existsAmenityByname(name))
            {
                return null;
            }
            else
            {
                System.out.println(this.repository.getByname(name));
                return this.repository.getByname(name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
