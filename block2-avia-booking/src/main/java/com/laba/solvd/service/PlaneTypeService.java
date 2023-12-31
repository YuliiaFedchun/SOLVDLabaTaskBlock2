package com.laba.solvd.service;

import com.laba.solvd.domain.PlaneType;

public interface PlaneTypeService {
    PlaneType create(PlaneType planeType);

    PlaneType findByName(String name);

    PlaneType findById(Long planeTypeId);
}
