package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;

public class PriorityBoardingDecorator implements TariffMolder {
    private final TariffMolder tariffMolder;

    public PriorityBoardingDecorator(TariffMolder tariffMolder) {
        this.tariffMolder = tariffMolder;
    }

    @Override
    public Tariff modelTariff(Tariff tariff) {
        tariff.setPriorityBoarding(true);
        tariffMolder.modelTariff(tariff);
        return tariff;
    }
}
