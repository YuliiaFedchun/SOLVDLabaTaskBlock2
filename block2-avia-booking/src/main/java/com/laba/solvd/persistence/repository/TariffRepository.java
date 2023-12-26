package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Tariff;

public interface TariffRepository {
    void create(Tariff tariff, Long airlineId);
}
