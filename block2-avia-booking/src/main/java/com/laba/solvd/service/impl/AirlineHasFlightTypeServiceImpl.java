package com.laba.solvd.service.impl;

import com.laba.solvd.persistence.impl.AirlineHasFlightTypeRepositoryImpl;
import com.laba.solvd.persistence.repository.AirlineHasFlightTypeRepository;
import com.laba.solvd.service.AirlineHasFlightTypeService;

public class AirlineHasFlightTypeServiceImpl implements AirlineHasFlightTypeService {
    private final AirlineHasFlightTypeRepository airlineHasFlightTypeRepository;

    AirlineHasFlightTypeServiceImpl() {
        this.airlineHasFlightTypeRepository = new AirlineHasFlightTypeRepositoryImpl();
    }

    @Override
    public void create(Long airlineId, Long flightTypeId) {
        airlineHasFlightTypeRepository.create(airlineId, flightTypeId);
    }
}
