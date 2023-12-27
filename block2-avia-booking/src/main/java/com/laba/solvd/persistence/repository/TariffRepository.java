package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Tariff;

import java.util.List;

public interface TariffRepository {
    void create(Tariff tariff, Long airlineId);
    List<Tariff> findAllByAirlineId(Long airlineId);
}
