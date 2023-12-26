package com.laba.solvd.service;

import com.laba.solvd.domain.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger create(Passenger passenger);

    Passenger findById(Long passengerId);

    List<Passenger> findAll();

    Passenger updateContactInfoById(String phoneNumber, String email, Long passengerId);

    void deleteById(Long passengerId);
}
