package com.laba.solvd.domain;

import com.laba.solvd.domain.enums.AirportClass;

public class Airport {
    private long id;
    private String name;
    private String city;
    private String country;
    private AirportClass class_name;

    public Airport(String name, String city, String country, AirportClass class_name) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.class_name = class_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AirportClass getClass_name() {
        return class_name;
    }

    public void setClass_name(AirportClass class_name) {
        this.class_name = class_name;
    }
}
