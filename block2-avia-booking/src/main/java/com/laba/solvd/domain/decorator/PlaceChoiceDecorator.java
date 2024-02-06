package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;

public class PlaceChoiceDecorator implements TariffMolder {
    private final TariffMolder tariffMolder;

    public PlaceChoiceDecorator(TariffMolder tariffMolder) {
        this.tariffMolder = tariffMolder;
    }

    @Override
    public Tariff modelTariff(Tariff tariff) {
        tariff.setPlaceChoice(true);
        tariffMolder.modelTariff(tariff);
        return tariff;
    }
}
