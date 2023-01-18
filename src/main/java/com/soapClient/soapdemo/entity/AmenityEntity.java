package com.soapClient.soapdemo.entity;

import com.onboarding.hotels.HotelDetails;

import javax.persistence.*;
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
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
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
}
