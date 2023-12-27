package com.laba.solvd.service;

import com.laba.solvd.domain.Airport;

public interface AirportService {
    Airport create(Airport airport);

    Airport findById(Long airportId);
    Long findIdByName(String name);

}
