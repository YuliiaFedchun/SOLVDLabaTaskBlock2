package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;

public interface TariffMolder {
    Tariff modelTariff(Tariff tariff);
}
