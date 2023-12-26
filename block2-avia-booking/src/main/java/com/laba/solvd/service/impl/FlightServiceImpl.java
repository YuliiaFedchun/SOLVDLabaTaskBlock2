package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.Airport;
import com.laba.solvd.domain.Flight;
import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.persistence.impl.FlightRepositoryImpl;
import com.laba.solvd.persistence.repository.FlightRepository;
import com.laba.solvd.service.*;

public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final AirlineService airlineService;
    private final DepartureService departureService;
    private final ArrivalService arrivalService;
    private final PlaneTypeService planeTypeService;
    private final AirportService airportService;

    public FlightServiceImpl() {
        this.flightRepository = new FlightRepositoryImpl();
        this.airlineService = new AirlineServiceImpl();
        this.arrivalService = new ArrivalServiceImpl();
        this.departureService = new DepartureServiceImpl();
        this.planeTypeService = new PlaneTypeServiceImpl();
        this.airportService = new AirportServiceImpl();
    }

    @Override
    public Flight create(Flight flight) {
        flight.setId(0);
        if (flight.getAirline() != null) {
            Airline airline = airlineService.create(flight.getAirline());
            flight.setAirline(airline);
        }
        if (flight.getDepartureAirport() != null) {
            Airport departureAirport = airportService.create(flight.getDepartureAirport());
            departureService.create(departureAirport.getId());
            flight.setDepartureAirport(departureAirport);
        }
        if (flight.getArrivalAirport() != null) {
            Airport arrivalAirport = airportService.create(flight.getArrivalAirport());
            arrivalService.create(arrivalAirport.getId());
            flight.setArrivalAirport(arrivalAirport);
        }
        if (flight.getPlaneType() != null) {
            PlaneType planeType = planeTypeService.create(flight.getPlaneType());
            flight.setPlaneType(planeType);
        }
        return flight;
    }

    @Override
    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId);
    }
}
