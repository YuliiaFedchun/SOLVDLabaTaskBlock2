package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Flight;

import java.util.List;

public interface FlightRepository {
    void create(Flight flight);

    Flight findById(Long flightId);

    List<Flight> findByDepartureId(Long departureId);

}
