package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Passenger;
import com.laba.solvd.domain.Passport;
import com.laba.solvd.persistence.repository.PassengerRepository;
import com.laba.solvd.persistence.impl.PassengerRepositoryImpl;
import com.laba.solvd.service.PassengerService;
import com.laba.solvd.service.PassportService;
import com.laba.solvd.service.TicketService;

import java.util.List;

public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassportService passportService;

    public PassengerServiceImpl() {
        this.passengerRepository = new PassengerRepositoryImpl();
        this.passportService = new PassportServiceImpl();
    }

    @Override
    public Passenger create(Passenger passenger) {
        passenger.setId(0);

        if (passenger.getPassport() != null) {
            Passport passport = passportService.create(passenger.getPassport());
            passenger.setPassport(passport);
        }
        passengerRepository.create(passenger);
        return passenger;
    }

    @Override
    public Passenger findById(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId);
        Passport passport =
                passportService.findById(passengerRepository.getPassportId(passengerId));
        passenger.setPassport(passport);
        return passenger;
    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger> passengers = passengerRepository.findAll();
        passengers.forEach(p -> {
                    Passport passport = passportService.findById(passengerRepository.getPassportId(p.getId()));
                    p.setPassport(passport);
                });
        return passengers;
    }

    @Override
    public Passenger updateContactInfoById(String phoneNumber, String email,
                                           Long passengerId) {
        if (findById(passengerId) != null) {
            passengerRepository.updateContactInfoById(phoneNumber, email, passengerId);
        }
        return findById(passengerId);
    }

    @Override
    public void deleteById(Long passengerId) {
        Passenger passenger = findById(passengerId);
        passportService.deleteById(passenger.getPassport().getId());
        passengerRepository.deleteById(passengerId);
    }
}
