package com.laba.solvd.domain;

import com.laba.solvd.domain.enums.FlightType;

import java.util.List;

public class Airline {
    private long id;
    private String name;
    private String code;
    private List<FlightType> flightTypes;
    private LuggageTariff luggageTariff;

    public Airline(String name, String code, List<FlightType> flightTypes) {
        this.name = name;
        this.code = code;
        this.flightTypes = flightTypes;
    }

    public Airline() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<FlightType> getFlightTypes() {
        return flightTypes;
    }

    public void setFlightTypes(List<FlightType> flightTypes) {
        this.flightTypes = flightTypes;
    }

    public LuggageTariff getLuggageTariff() {
        return luggageTariff;
    }

    public void setLuggageTariff(LuggageTariff luggageTariff) {
        this.luggageTariff = luggageTariff;
    }
}
