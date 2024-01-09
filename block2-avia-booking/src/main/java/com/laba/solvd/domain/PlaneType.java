package com.laba.solvd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneType {
    @JsonIgnore
    @XmlTransient
    private long id;
    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "seatsNumber")
    private int seatsNumber;

    public PlaneType(String name, int seatsNumber) {
        this.name = name;
        this.seatsNumber = seatsNumber;
    }

    public PlaneType() {
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

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Override
    public String toString() {
        return "PlaneType{" +
                "name='" + name + '\'' +
                ", seatsNumber='" + seatsNumber + '\'' +
                '}';
    }
}
