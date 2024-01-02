package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Airport;
import org.apache.ibatis.annotations.Param;

public interface AirportRepository {
    void create(@Param("airport") Airport airport,
                @Param("airportTypeId") Long airportTypeId);

    Airport findById(Long airportId);

    Airport findByName(String name);

    Long getAirportTypeId(Long airportId);
}
