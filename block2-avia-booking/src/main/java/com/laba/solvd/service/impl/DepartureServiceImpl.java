package com.laba.solvd.service.impl;

import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.DepartureRepository;
import com.laba.solvd.service.DepartureService;
import org.apache.ibatis.session.SqlSession;

public class DepartureServiceImpl implements DepartureService {
    private final DepartureRepository departureRepository;

    public DepartureServiceImpl() {
        //this.departureRepository = new DepartureRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.departureRepository = sqlSession.getMapper(DepartureRepository.class);
    }

    @Override
    public void create(Long airportId) {
        departureRepository.create(airportId);
    }
}
