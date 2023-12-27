package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Airport;
import com.laba.solvd.persistence.repository.AirportRepository;
import com.laba.solvd.persistence.impl.AirportRepositoryImpl;
import com.laba.solvd.service.AirportService;
import com.laba.solvd.service.ArrivalService;
import com.laba.solvd.service.DepartureService;

public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final DepartureService departureService;
    private final ArrivalService arrivalService;

    public AirportServiceImpl() {
        this.airportRepository = new AirportRepositoryImpl();
        this.departureService = new DepartureServiceImpl();
        this.arrivalService = new ArrivalServiceImpl();
    }

    @Override
    public Airport create(Airport airport) {
        airport.setId(0);
        airportRepository.create(airport);
        departureService.create(airport.getId());
        arrivalService.create(airport.getId());
        return airport;
    }

    @Override
    public Airport findById(Long airportId) {
        return airportRepository.findById(airportId);
    }

    @Override
    public Long findIdByName(String name) {
        return airportRepository.findIdByName(name);
    }
}
