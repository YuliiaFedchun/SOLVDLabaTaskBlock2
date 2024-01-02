package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Tariff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TariffRepository {
    void create(@Param("Tariff") Tariff tariff,
                @Param("airlineId") Long airlineId,
                @Param("serviceClassId") Long serviceClassId);

    List<Tariff> findAllByAirlineId(Long airlineId);

    Long getServiceClassId(Long tariffId);
}
