package com.laba.solvd.domain;

public class PlaneType {
    private long id;
    private String name;
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
                '}';
    }
}
