package com.laba.solvd.domain;

import com.laba.solvd.domain.enums.AirportType;

public class Airport {
    private long id;
    private String name;
    private String city;
    private String country;
    private AirportType typeName;

    public Airport(String name, String city, String country, AirportType typeName) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.typeName = typeName;
    }
    public Airport(){}

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

    public AirportType getTypeName() {
        return typeName;
    }

    public void setTypeName(AirportType typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
