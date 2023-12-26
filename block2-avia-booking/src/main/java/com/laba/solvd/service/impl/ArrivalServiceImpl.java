package com.laba.solvd.service.impl;

import com.laba.solvd.persistence.repository.ArrivalRepository;
import com.laba.solvd.persistence.impl.ArrivalRepositoryImpl;
import com.laba.solvd.service.ArrivalService;

public class ArrivalServiceImpl implements ArrivalService {
    private final ArrivalRepository arrivalRepository;

    public ArrivalServiceImpl() {
        this.arrivalRepository = new ArrivalRepositoryImpl();
    }

    @Override
    public void create(Long airportId) {
        arrivalRepository.create(airportId);
    }
}
