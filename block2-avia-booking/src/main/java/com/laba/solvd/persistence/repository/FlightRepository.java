package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Flight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightRepository {
    void create(@Param("flight") Flight flight,
                @Param("airlineId") Long airlineId,
                @Param("departureId") Long departureId,
                @Param("arrivalId") Long arrivalId,
                @Param("planeTypeId") Long planeTypeId);

    Flight findById(Long flightId);

    List<Flight> findByDepartureId(Long departureId);

    Long getAirlineId(Long flightId);

    Long getDepartureId(Long flightId);

    Long getArrivalId(Long flightId);

    Long getPlaneTypeId(Long flightId);

}
