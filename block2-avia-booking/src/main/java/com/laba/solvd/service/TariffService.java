package com.laba.solvd.service;

import com.laba.solvd.domain.Tariff;

import java.util.List;

public interface TariffService {
    Tariff create(Tariff tariff, Long airlineId, Long serviceClassId);

    Tariff findByName(String name, Long airlineId);

    List<Tariff> findAllByAirlineId(Long airlineId);

    Long getServiceClassId(Long tariffId);
}
