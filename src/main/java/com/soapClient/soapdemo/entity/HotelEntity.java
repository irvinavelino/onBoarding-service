package com.soapClient.soapdemo.entity;

import com.onboarding.hotels.Amenities;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="hotels")
public class HotelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int hotelId;
    private String name;
    private String address;
    private float rating;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            },mappedBy = "hotelEntities")
    private Set<AmenityEntity> amenityEntities;
    public HotelEntity() {

    }

    public Set<AmenityEntity> getAmenityEntities() {
        return amenityEntities;
    }

    public void setAmenityEntities(Set<AmenityEntity> amenityEntities) {
        this.amenityEntities = amenityEntities;
    }

    public void addAmenity(AmenityEntity amenityEntity){
        if(this.getAmenityEntities()==null)
        {
            this.setAmenityEntities(Collections.singleton(amenityEntity));
        }else {
            this.amenityEntities.add(amenityEntity);
            amenityEntity.getHotelEntities().add(this);
        }
    }
    public void removeAmenity(int amenityId)
    {
        AmenityEntity amenityEntity=this.amenityEntities.stream().filter(h->h.getAmenityId()==amenityId).findFirst().orElse(null);
        if(amenityEntity!=null)
        {
            this.amenityEntities.remove(amenityEntity);
            amenityEntity.getHotelEntities().remove(this);
        }
    }

    public HotelEntity(String name, String address, float rating) {
        this.name = name;
        this.address = address;
        this.rating = rating;
    }
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }


}