package com.laba.solvd.service.impl;

import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.ArrivalRepository;
import com.laba.solvd.service.ArrivalService;
import org.apache.ibatis.session.SqlSession;

public class ArrivalServiceImpl implements ArrivalService {
    private final ArrivalRepository arrivalRepository;

    public ArrivalServiceImpl() {
        //this.arrivalRepository = new ArrivalRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.arrivalRepository = sqlSession.getMapper(ArrivalRepository.class);
    }

    @Override
    public void create(Long airportId) {
        arrivalRepository.create(airportId);
    }
}
