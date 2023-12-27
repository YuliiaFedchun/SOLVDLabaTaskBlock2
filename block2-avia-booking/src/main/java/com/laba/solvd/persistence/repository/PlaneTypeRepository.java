package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.PlaneType;

public interface PlaneTypeRepository {
    void create(PlaneType planeType);
    PlaneType findByName(String name);
}
