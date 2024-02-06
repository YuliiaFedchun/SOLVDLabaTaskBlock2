package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Flight;
import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.FlightRepository;
import com.laba.solvd.service.AirlineService;
import com.laba.solvd.service.AirportService;
import com.laba.solvd.service.FlightService;
import com.laba.solvd.service.PlaneTypeService;
import org.apache.ibatis.session.SqlSession;
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
        //this.flightRepository = new FlightRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.flightRepository = sqlSession.getMapper(FlightRepository.class);
        this.airlineService = new AirlineServiceImpl();
        this.planeTypeService = new PlaneTypeServiceImpl();
        this.airportService = new AirportServiceImpl();
    }

    @Override
    public Flight create(Flight flight, Long airlineId, Long departureId, Long arrivalId) {
        flight = Flight.builder(flight).id(0).build();
        if (airlineService.findById(airlineId) != null) {
            if (airportService.findById(departureId) != null &&
                    airportService.findById(arrivalId) != null) {
                flight = Flight.builder(flight)
                        .airline(airlineService.findById(airlineId))
                        .departureAirport(airportService.findById(departureId))
                        .arrivalAirport(airportService.findById(arrivalId))
                        .build();
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
            }
            flight = Flight.builder(flight).planeType(planeType).build();
        }
        flightRepository.create(flight, airlineId, departureId, arrivalId,
                flight.getPlaneType().getId());
        return flight;
    }

    @Override
    public Flight findById(Long flightId) {
        Flight flight = flightRepository.findById(flightId);
        flight = Flight.builder(flight)
                .airline(airlineService.findById(flightRepository.getAirlineId(flightId)))
                .departureAirport(airportService.findById(flightRepository.getDepartureId(flightId)))
                .arrivalAirport(airportService.findById(flightRepository.getArrivalId(flightId)))
                .planeType(planeTypeService.findById(flightRepository.getPlaneTypeId(flightId)))
                .build();
        return flight;
    }

    @Override
    public List<Flight> findByDepartureAirportId(Long departureAirportId) {
        return flightRepository.findByDepartureId(departureAirportId);
    }

    @Override
    public List<Flight> findByDepartureAirportName(String departureAirportName) {
        Long departureAirportId = airportService.findByName(departureAirportName).getId();
        return findByDepartureAirportId(departureAirportId);
    }
}
