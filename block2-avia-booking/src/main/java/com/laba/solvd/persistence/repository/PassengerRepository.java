package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Passenger;

import java.util.List;

public interface PassengerRepository {
    void create(Passenger passenger);

    void deleteById(Long passengerId);

    Passenger findById(Long passengerId);

    Long getPassportId(Long passengerId);

    List<Passenger> findAll();

    void updateContactInfoById(String phoneNumber, String email, Long passengerId);
}
