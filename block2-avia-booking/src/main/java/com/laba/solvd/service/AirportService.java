package com.laba.solvd.service;

import com.laba.solvd.domain.Airport;

public interface AirportService {
    Airport create(Airport airport);

    Airport findById(Long airportId);

    Airport findByName(String name);

    Long getAirportTypeId(Long airportId);

}
