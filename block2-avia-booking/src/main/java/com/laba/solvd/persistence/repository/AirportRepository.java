package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Airport;

public interface AirportRepository {
    void create(Airport airport);
    Airport findById(Long airportId);
    Long findIdByName(String name);
}