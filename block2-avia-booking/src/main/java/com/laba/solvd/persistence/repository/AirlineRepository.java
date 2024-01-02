package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Airline;
import org.apache.ibatis.annotations.Param;

public interface AirlineRepository {
    void create(@Param("airline") Airline airline,
                @Param("luggageTariffId") Long luggageTariffId);

    Airline findById(Long airlineId);
}
