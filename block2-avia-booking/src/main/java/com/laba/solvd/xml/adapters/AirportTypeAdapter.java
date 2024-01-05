package com.laba.solvd.xml.adapters;

import com.laba.solvd.domain.enums.AirportType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class AirportTypeAdapter extends XmlAdapter<String, AirportType> {
    @Override
    public AirportType unmarshal(String s) {
        return AirportType.valueOf(s.toUpperCase());
    }

    @Override
    public String marshal(AirportType airportType) {
        return airportType.getName();
    }
}
