package com.laba.solvd.service.impl;

import com.laba.solvd.Main;
import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.Airport;
import com.laba.solvd.domain.Flight;
import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.persistence.impl.FlightRepositoryImpl;
import com.laba.solvd.persistence.repository.FlightRepository;
import com.laba.solvd.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FlightServiceImpl implements FlightService {
    private static final Logger LOGGER = LogManager.getLogger(FlightServiceImpl.class);
    private final FlightRepository flightRepository;
    private final AirlineService airlineService;
    private final PlaneTypeService planeTypeService;
    private final AirportService airportService;

    public FlightServiceImpl() {
        this.flightRepository = new FlightRepositoryImpl();
        this.airlineService = new AirlineServiceImpl();
        this.planeTypeService = new PlaneTypeServiceImpl();
        this.airportService = new AirportServiceImpl();
    }

    @Override
    public Flight create(Flight flight, Long airlineId, Long departureId, Long arrivalId) {
        flight.setId(0);
        if (airlineService.findById(airlineId) != null) {
            if(airportService.findById(departureId) != null &&
                    airportService.findById(arrivalId) != null) {
                flightRepository.create(flight);
                flight.setAirline(airlineService.findById(airlineId));
                flight.setDepartureAirport(airportService.findById(departureId));
                flight.setArrivalAirport(airportService.findById(arrivalId));
            } else {
                LOGGER.info("Incorrect departure or arrival");
            }
        } else {
            LOGGER.info("Incorrect airline");
        }

        if (flight.getPlaneType() != null) {
            PlaneType planeType;
            if (planeTypeService.findByName(flight.getPlaneType().getName()) == null) {
                planeType = planeTypeService.create(flight.getPlaneType());
            } else {
                planeType = planeTypeService.findByName(flight.getPlaneType().getName());
                flight.setPlaneType(planeType);
            }
            flight.setPlaneType(planeType);
        }
        return flight;
    }

    @Override
    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId);
    }

    @Override
    public List<Flight> findByDepartureAirportId(Long departureAirportId) {
        return flightRepository.findByDepartureId(departureAirportId);
    }

    @Override
    public List<Flight> findByDepartureAirportName(String departureAirportName) {
        Long departureAirportId = airportService.findIdByName(departureAirportName);
        return findByDepartureAirportId(departureAirportId);
    }
}
