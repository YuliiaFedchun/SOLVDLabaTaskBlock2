package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Flight;

public interface FlightRepository {
    void create(Flight flight);

    Flight findById(Long flightId);

}
