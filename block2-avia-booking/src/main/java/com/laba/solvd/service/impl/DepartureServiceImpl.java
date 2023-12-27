package com.laba.solvd.service.impl;

import com.laba.solvd.persistence.repository.DepartureRepository;
import com.laba.solvd.persistence.impl.DepartureRepositoryImpl;
import com.laba.solvd.service.DepartureService;

public class DepartureServiceImpl implements DepartureService {
    private final DepartureRepository departureRepository;

    public DepartureServiceImpl() {
        this.departureRepository = new DepartureRepositoryImpl();
    }

    @Override
    public void create(Long airportId) {
        departureRepository.create(airportId);
    }

    @Override
    public Long getDepartureId(Long airportId) {
        return departureRepository.getDepartureId(airportId);
    }
}
