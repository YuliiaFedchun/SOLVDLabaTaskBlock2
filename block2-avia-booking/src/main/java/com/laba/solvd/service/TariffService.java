package com.laba.solvd.service;

import com.laba.solvd.domain.Tariff;

public interface TariffService {
    Tariff create(Tariff tariff, Long airlineId);
}
