package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;

public class FastTrackDecorator implements TariffMolder {
    private final TariffMolder tariffMolder;

    public FastTrackDecorator(TariffMolder tariffMolder) {
        this.tariffMolder = tariffMolder;
    }

    @Override
    public Tariff modelTariff(Tariff tariff) {
        tariff.setFastTrack(true);
        tariffMolder.modelTariff(tariff);
        return tariff;
    }
}
