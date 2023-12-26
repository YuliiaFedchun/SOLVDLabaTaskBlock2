package com.laba.solvd.persistence.repository;

public interface AirlineHasFlightTypeRepository {
    void create(Long airlineId, Long flightTypeId);
}
