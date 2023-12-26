package com.laba.solvd.domain;

import com.laba.solvd.domain.enums.ServiceClass;

public class Tariff {
    private long id;
    private String name;
    private int handLuggage;
    private int registerLuggage;
    private boolean placeChoice;
    private boolean fastTrack;
    private boolean priorityBoarding;
    private Airline airline;
    private ServiceClass serviceClass;
    private double basePrice;

    public Tariff(String name, int handLuggage, int registerLuggage, boolean placeChoice,
                  boolean fastTrack, boolean priorityBoarding, Airline airline,
                  ServiceClass serviceClass, double basePrice) {
        this.name = name;
        this.handLuggage = handLuggage;
        this.registerLuggage = registerLuggage;
        this.placeChoice = placeChoice;
        this.fastTrack = fastTrack;
        this.priorityBoarding = priorityBoarding;
        this.airline = airline;
        this.serviceClass = serviceClass;
        this.basePrice = basePrice;
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

    public int getHandLuggage() {
        return handLuggage;
    }

    public void setHandLuggage(int handLuggage) {
        this.handLuggage = handLuggage;
    }

    public int getRegisterLuggage() {
        return registerLuggage;
    }

    public void setRegisterLuggage(int registerLuggage) {
        this.registerLuggage = registerLuggage;
    }

    public boolean hasPlaceChoice() {
        return placeChoice;
    }

    public void setPlaceChoice(boolean placeChoice) {
        this.placeChoice = placeChoice;
    }

    public boolean hasFastTrack() {
        return fastTrack;
    }

    public void setFastTrack(boolean fastTrack) {
        this.fastTrack = fastTrack;
    }

    public boolean hasPriorityBoarding() {
        return priorityBoarding;
    }

    public void setPriorityBoarding(boolean priorityBoarding) {
        this.priorityBoarding = priorityBoarding;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public ServiceClass getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
