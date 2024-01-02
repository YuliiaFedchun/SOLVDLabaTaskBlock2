package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Airport;
import com.laba.solvd.domain.enums.AirportType;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.AirportRepository;
import com.laba.solvd.service.AirportService;
import com.laba.solvd.service.ArrivalService;
import com.laba.solvd.service.DepartureService;
import org.apache.ibatis.session.SqlSession;

public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final DepartureService departureService;
    private final ArrivalService arrivalService;

    public AirportServiceImpl() {
        //this.airportRepository = new AirportRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.airportRepository = sqlSession.getMapper(AirportRepository.class);
        this.departureService = new DepartureServiceImpl();
        this.arrivalService = new ArrivalServiceImpl();
    }

    @Override
    public Airport create(Airport airport) {
        airport.setId(0);
        airportRepository.create(airport, airport.getTypeName().getId());
        departureService.create(airport.getId());
        arrivalService.create(airport.getId());
        return airport;
    }

    @Override
    public Airport findById(Long airportId) {
        Airport airport = airportRepository.findById(airportId);
        Long airportTypeId = airportRepository.getAirportTypeId(airportId);
        for (AirportType airportType : AirportType.values()) {
            if (airportType.getId().equals(airportTypeId)) {
                airport.setTypeName(airportType);
            }
        }
        return airport;
    }

    @Override
    public Airport findByName(String name) {
        Airport airport = airportRepository.findByName(name);
        Long airportTypeId = airportRepository.getAirportTypeId(airport.getId());
        for (AirportType airportType : AirportType.values()) {
            if (airportType.getId().equals(airportTypeId)) {
                airport.setTypeName(airportType);
            }
        }
        return airport;
    }

    @Override
    public Long getAirportTypeId(Long airportId) {
        return airportRepository.getAirportTypeId(airportId);
    }
}
