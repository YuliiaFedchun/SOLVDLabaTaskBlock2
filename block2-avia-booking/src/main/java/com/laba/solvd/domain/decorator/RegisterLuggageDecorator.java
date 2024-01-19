package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;

public class RegisterLuggageDecorator implements TariffMolder {
    private final TariffMolder tariffMolder;

    public RegisterLuggageDecorator(TariffMolder tariffMolder) {
        this.tariffMolder = tariffMolder;
    }

    @Override
    public Tariff modelTariff(Tariff tariff) {
        int registerLuggage = tariff.getRegisterLuggage();
        tariff.setRegisterLuggage(++registerLuggage);
        tariffMolder.modelTariff(tariff);
        return tariff;
    }
}
