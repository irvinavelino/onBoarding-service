package com.soapClient.soapdemo.entity;

import com.onboarding.hotels.HotelDetails;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Amenity")
public class AmenityEntity
{
    @Id
    @GeneratedValue
    private int amenityId;
    private String name;
    private String details;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "amenityId"),
            inverseJoinColumns = @JoinColumn(name = "hotelId"))
    private Set<HotelEntity> hotelEntities=new HashSet<>();
    public AmenityEntity() {
    }

    public Set<HotelEntity> getHotelEntities() {
        return hotelEntities;
    }

    public void setHotelEntities(Set<HotelEntity> hotelEntities) {
        this.hotelEntities = hotelEntities;
    }

    public AmenityEntity(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public void addHotel(HotelEntity hotelEntity){
            this.hotelEntities.add(hotelEntity);
            if(hotelEntity.getAmenityEntities()==null)
            {
                hotelEntity.setAmenityEntities(Collections.singleton(this));
            }else{
                hotelEntity.getAmenityEntities().add(this);
            }
    }
    public void removeHotel(int hotelId)
    {
        HotelEntity hotelEntity=this.hotelEntities.stream().filter(h->h.getHotelId()==hotelId).findFirst().orElse(null);
        if(hotelEntity!=null)
        {
            this.hotelEntities.remove(hotelEntity);
            hotelEntity.getAmenityEntities().remove(this);
        }
    }
}
