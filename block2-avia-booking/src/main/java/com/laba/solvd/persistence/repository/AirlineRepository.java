package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Airline;

public interface AirlineRepository {
    void create(Airline airline);

    Airline findById(Long airlineId);
}
