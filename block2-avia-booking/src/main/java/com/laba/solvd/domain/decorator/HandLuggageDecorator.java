package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;


public class HandLuggageDecorator implements TariffMolder {
    private final TariffMolder tariffMolder;

    public HandLuggageDecorator(TariffMolder tariffMolder) {
        this.tariffMolder = tariffMolder;
    }

    @Override
    public Tariff modelTariff(Tariff tariff) {
        int handLuggage = tariff.getHandLuggage();
        tariff.setHandLuggage(++handLuggage);
        tariffMolder.modelTariff(tariff);
        return tariff;
    }
}
