package com.laba.solvd.service;

import com.laba.solvd.domain.Airline;

public interface AirlineService {
    Airline create(Airline airline);

    Airline findById(Long airlineId);
}
