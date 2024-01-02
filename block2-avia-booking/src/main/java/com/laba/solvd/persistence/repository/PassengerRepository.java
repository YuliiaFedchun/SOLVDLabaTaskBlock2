package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Passenger;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PassengerRepository {
    void create(@Param("passenger") Passenger passenger,
                @Param("passportId") Long passportId);

    void deleteById(Long passengerId);

    Passenger findById(Long passengerId);

    Long getPassportId(Long passengerId);

    List<Passenger> findAll();

    void updateContactInfoById(@Param("phoneNumber") String phoneNumber,
                               @Param("email") String email,
                               @Param("passengerId") Long passengerId);
}
