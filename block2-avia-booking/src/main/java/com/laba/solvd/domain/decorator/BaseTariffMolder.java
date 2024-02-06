package com.laba.solvd.domain.decorator;

import com.laba.solvd.domain.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTariffMolder implements TariffMolder {
    private static final Logger LOGGER = LogManager.getLogger(BaseTariffMolder.class);


    @Override
    public Tariff modelTariff(Tariff tariff) {
        if (tariff.getName() != null && tariff.getAirline() != null &&
                tariff.getServiceClass() != null && tariff.getBasePrice() != 0) {
            LOGGER.info("Base tariff was create: " + tariff);
        } else {
            LOGGER.info("You have to add some info.");
        }
        return tariff;
    }
}
