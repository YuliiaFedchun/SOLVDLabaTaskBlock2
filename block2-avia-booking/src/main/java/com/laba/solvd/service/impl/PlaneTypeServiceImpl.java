package com.laba.solvd.service.impl;

import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.persistence.repository.PlaneTypeRepository;
import com.laba.solvd.persistence.impl.PlaneTypeRepositoryImpl;
import com.laba.solvd.service.PlaneTypeService;

public class PlaneTypeServiceImpl implements PlaneTypeService {
    private final PlaneTypeRepository planeTypeRepository;

    public PlaneTypeServiceImpl() {
        this.planeTypeRepository = new PlaneTypeRepositoryImpl();
    }

    @Override
    public PlaneType create(PlaneType planeType) {
        planeType.setId(0);
        planeTypeRepository.create(planeType);
        return planeType;
    }
}
