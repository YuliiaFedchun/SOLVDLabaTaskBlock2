package com.laba.solvd;

import com.laba.solvd.domain.*;
import com.laba.solvd.domain.enums.AirportType;
import com.laba.solvd.domain.enums.FlightType;
import com.laba.solvd.service.*;
import com.laba.solvd.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static {
        System.setProperty("log4j2.configurationFile", "log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Passport passport1 = new Passport("MO367567");
        PassportService passportService = new PassportServiceImpl();
        //passportService.create(passport1);
        LOGGER.info(passportService.findById(1L));
        //passportService.deleteById(4L);

        PassengerService passengerService = new PassengerServiceImpl();
        LOGGER.info(passengerService.findById(1L));
        Passenger passenger1 = new Passenger("Katrin", "Gray", 40,
                "0957689945", "k_gray@gmail.com");
        passenger1.setPassport(passport1);
        passengerService.create(passenger1);
        LOGGER.info(passengerService.findAll());
        LOGGER.info(passengerService.updateContactInfoById("0356789999", "white@gmail.com", 1L));
        passengerService.deleteById(3L);


        LuggageTariff luggageTariff1 = new LuggageTariff(45.0, 75.0);
        List<FlightType> flightTypeList = new ArrayList<>();
        flightTypeList.add(FlightType.REGULAR);
        flightTypeList.add(FlightType.LOWCOST);
        AirlineService airlineService = new AirlineServiceImpl();
        Airline airline1 = new Airline("LOT Polish Airlines",
                "LOT", flightTypeList);
        airline1.setLuggageTariff(luggageTariff1);
        airlineService.create(airline1);
        LOGGER.info(airlineService.findById(2L));

        AirportService airportService = new AirportServiceImpl();
        Airport airport = new Airport("Boryspil", "Kyiv", "Ukraine",
                AirportType.INTERNATIONAL);
        airportService.create(airport);
        LOGGER.info(airportService.findById(2L));

        PlaneTypeService planeTypeService = new PlaneTypeServiceImpl();
        PlaneType planeType1 = new PlaneType("Boeing-767", 500);
        planeTypeService.create(planeType1);

        FlightService flightService = new FlightServiceImpl();
        LOGGER.info(flightService.findById(2L));

        TariffService tariffService = new TariffServiceImpl();
        LOGGER.info(tariffService.findAllByAirlineId(1L));

        TicketService ticketService = new TicketServiceImpl();
        ticketService.deleteByPassengerId(2L);
    }
}
