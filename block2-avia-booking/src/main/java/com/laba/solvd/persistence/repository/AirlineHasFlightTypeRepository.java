package com.laba.solvd.persistence.repository;

import org.apache.ibatis.annotations.Param;

public interface AirlineHasFlightTypeRepository {
    void create(@Param("airlineId") Long airlineId,
                @Param("flightTypeId") Long flightTypeId);
}
