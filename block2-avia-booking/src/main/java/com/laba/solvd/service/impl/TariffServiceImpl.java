package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.Tariff;
import com.laba.solvd.persistence.impl.TariffRepositoryImpl;
import com.laba.solvd.persistence.repository.TariffRepository;
import com.laba.solvd.service.AirlineService;
import com.laba.solvd.service.TariffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffServiceImpl implements TariffService {
    private static final Logger LOGGER = LogManager.getLogger(TariffServiceImpl.class);
    private final TariffRepository tariffRepository;
    private final AirlineService airlineService;

    public TariffServiceImpl() {
        this.tariffRepository = new TariffRepositoryImpl();
        this.airlineService = new AirlineServiceImpl();
    }

    @Override
    public Tariff create(Tariff tariff, Long airlineId) {
        tariff.setId(0);
        if (airlineService.findById(airlineId) != null) {
            tariffRepository.create(tariff, airlineId);
        } else {
            LOGGER.info("You have to create airline first");
        }
        return tariff;
    }
}
