package com.soapClient.soapdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="hotels")
public class HotelEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int hotelId;
    private String name;
    private String address;
    private float rating;
    @ElementCollection
    private List<String> amenities= new ArrayList<>();

    public HotelEntity() {

    }

    public HotelEntity(String name, String address, float rating, List<String> amenities) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.amenities = amenities;
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

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities=amenities;
    }
}