package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.LuggageTariff;
import com.laba.solvd.domain.enums.FlightType;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.AirlineRepository;
import com.laba.solvd.service.AirlineHasFlightTypeService;
import com.laba.solvd.service.AirlineService;
import com.laba.solvd.service.LuggageTariffService;
import org.apache.ibatis.session.SqlSession;

public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final LuggageTariffService luggageTariffService;
    private final AirlineHasFlightTypeService airlineHasFlightTypeService;

    public AirlineServiceImpl() {
        //this.airlineRepository = new AirlineRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.airlineRepository = sqlSession.getMapper(AirlineRepository.class);
        this.luggageTariffService = new LuggageTariffServiceImpl();
        this.airlineHasFlightTypeService = new AirlineHasFlightTypeServiceImpl();
    }

    @Override
    public Airline create(Airline airline) {
        airline.setId(0);
        if (airline.getLuggageTariff() != null) {
            LuggageTariff luggageTariff =
                    luggageTariffService.create(airline.getLuggageTariff());
            airline.setLuggageTariff(luggageTariff);
        }
        airlineRepository.create(airline, airline.getLuggageTariff().getId());

        if (airline.getFlightTypes() != null) {
            for (FlightType flightType : airline.getFlightTypes()) {
                airlineHasFlightTypeService.create(airline.getId(), flightType.getId());
            }
        }

        return airline;
    }

    @Override
    public Airline findById(Long airlineId) {

        return airlineRepository.findById(airlineId);
    }
}
