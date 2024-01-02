package com.laba.solvd.service.impl;

import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.AirlineHasFlightTypeRepository;
import com.laba.solvd.service.AirlineHasFlightTypeService;
import org.apache.ibatis.session.SqlSession;

public class AirlineHasFlightTypeServiceImpl implements AirlineHasFlightTypeService {
    private final AirlineHasFlightTypeRepository airlineHasFlightTypeRepository;

    AirlineHasFlightTypeServiceImpl() {
        //this.airlineHasFlightTypeRepository = new AirlineHasFlightTypeRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.airlineHasFlightTypeRepository =
                sqlSession.getMapper(AirlineHasFlightTypeRepository.class);
    }

    @Override
    public void create(Long airlineId, Long flightTypeId) {
        airlineHasFlightTypeRepository.create(airlineId, flightTypeId);
    }
}
