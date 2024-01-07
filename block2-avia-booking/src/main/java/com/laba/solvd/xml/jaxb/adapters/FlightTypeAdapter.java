package com.laba.solvd.xml.jaxb.adapters;

import com.laba.solvd.domain.enums.FlightType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class FlightTypeAdapter extends XmlAdapter<String, FlightType> {

    @Override
    public FlightType unmarshal(String s) throws Exception {
        return FlightType.valueOf(s.toUpperCase());
    }

    @Override
    public String marshal(FlightType flightType) throws Exception {
        return flightType.getType();
    }
}
