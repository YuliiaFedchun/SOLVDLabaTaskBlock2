package com.laba.solvd.service;

import com.laba.solvd.domain.Flight;

import java.util.List;

public interface FlightService {
    Flight create(Flight flight, Long airlineId, Long departureId, Long arrivalId);

    Flight findById(Long flightId);

    List<Flight> findByDepartureAirportId(Long departureAirportId);
    List<Flight> findByDepartureAirportName(String departureAirportName);
}
