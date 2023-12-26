package com.laba.solvd.service;

import com.laba.solvd.domain.Flight;

public interface FlightService {
    Flight create(Flight flight);

    Flight findById(Long flightId);
}
