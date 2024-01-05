package com.laba.solvd.xml.adapters;

import com.laba.solvd.domain.enums.FlightType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class FlightTypesAdapter extends XmlAdapter<List<String>, List<FlightType>> {

    @Override
    public List<FlightType> unmarshal(List<String> strings) throws Exception {
        return strings.stream()
                .map(s -> FlightType.valueOf(s.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> marshal(List<FlightType> flightTypes) throws Exception {
        return flightTypes.stream()
                .map(FlightType::getType)
                .collect(Collectors.toList());
    }
}
