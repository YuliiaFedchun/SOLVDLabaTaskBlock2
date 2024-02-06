package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;

public class TariffCreator {
    public static Tariff createTariff(Tariff tariff, boolean handLuggage,
                                      boolean registerLuggage, boolean placeChoice,
                                      boolean fastTrack, boolean priorityBoarding) {
        TariffMolder tariffMolder = new BaseTariffMolder();
        if (handLuggage) {
            tariffMolder = new HandLuggageDecorator(tariffMolder);
        }
        if (registerLuggage) {
            tariffMolder = new RegisterLuggageDecorator(tariffMolder);
        }
        if (placeChoice) {
            tariffMolder = new PlaceChoiceDecorator(tariffMolder);
        }
        if (fastTrack) {
            tariffMolder = new FastTrackDecorator(tariffMolder);
        }
        if (priorityBoarding) {
            tariffMolder = new PriorityBoardingDecorator(tariffMolder);
        }
        tariffMolder.modelTariff(tariff);
        return tariff;

    }
}
