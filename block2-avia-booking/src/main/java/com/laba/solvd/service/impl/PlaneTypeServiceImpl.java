package com.laba.solvd.service.impl;

import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.PlaneTypeRepository;
import com.laba.solvd.service.PlaneTypeService;
import org.apache.ibatis.session.SqlSession;

public class PlaneTypeServiceImpl implements PlaneTypeService {
    private final PlaneTypeRepository planeTypeRepository;

    public PlaneTypeServiceImpl() {
        //this.planeTypeRepository = new PlaneTypeRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.planeTypeRepository = sqlSession.getMapper(PlaneTypeRepository.class);
    }

    @Override
    public PlaneType create(PlaneType planeType) {
        planeType.setId(0);
        planeTypeRepository.create(planeType);
        return planeType;
    }

    @Override
    public PlaneType findByName(String name) {
        return planeTypeRepository.findByName(name);
    }

    @Override
    public PlaneType findById(Long planeTypeId) {
        return planeTypeRepository.findById(planeTypeId);
    }
}
