package com.laba.solvd.service.impl;

import com.laba.solvd.domain.LuggageTariff;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.LuggageTariffRepository;
import com.laba.solvd.service.LuggageTariffService;
import org.apache.ibatis.session.SqlSession;

public class LuggageTariffServiceImpl implements LuggageTariffService {
    private final LuggageTariffRepository luggageTariffRepository;

    public LuggageTariffServiceImpl() {
        //this.luggageTariffRepository = new LuggageTariffRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.luggageTariffRepository = sqlSession.getMapper(LuggageTariffRepository.class);
    }

    @Override
    public LuggageTariff create(LuggageTariff luggageTariff) {
        luggageTariff.setId(0);
        luggageTariffRepository.create(luggageTariff);
        return luggageTariff;
    }
}
