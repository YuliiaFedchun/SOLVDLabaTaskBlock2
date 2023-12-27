package com.laba.solvd;

import com.laba.solvd.domain.Passport;
import com.laba.solvd.service.FlightService;
import com.laba.solvd.service.PassengerService;
import com.laba.solvd.service.PassportService;
import com.laba.solvd.service.TariffService;
import com.laba.solvd.service.impl.FlightServiceImpl;
import com.laba.solvd.service.impl.PassengerServiceImpl;
import com.laba.solvd.service.impl.PassportServiceImpl;
import com.laba.solvd.service.impl.TariffServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static {
        System.setProperty("log4j2.configurationFile", "log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
       Passport passport1 = new Passport("MO367567");
        PassportService passportService = new PassportServiceImpl();
        passportService.create(passport1);

        PassengerService passengerService = new PassengerServiceImpl();
        LOGGER.info(passengerService.findById(1L));
        LOGGER.info(passengerService.findAll());
        LOGGER.info(passengerService.updateContactInfoById("0356789999","white@gmail.com", 1L));
        passengerService.deleteById(3L);

        FlightService flightService = new FlightServiceImpl();
        LOGGER.info(flightService.findByDepartureAirportId(1L));

        TariffService tariffService = new TariffServiceImpl();
        LOGGER.info(tariffService.findAllByAirlineId(1L));
    }
}
