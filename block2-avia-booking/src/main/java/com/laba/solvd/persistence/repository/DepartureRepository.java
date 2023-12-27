package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Airport;

public interface DepartureRepository {
    void create(Long airportId);
    Long getDepartureId(Long airportId);
}
