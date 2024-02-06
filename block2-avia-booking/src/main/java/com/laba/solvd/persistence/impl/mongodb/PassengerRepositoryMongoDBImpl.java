package com.laba.solvd.persistence.impl.mongodb;

import com.laba.solvd.domain.Passenger;
import com.laba.solvd.persistence.repository.PassengerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PassengerRepositoryMongoDBImpl implements PassengerRepository {
    private static final Logger LOGGER = LogManager.getLogger(PassengerRepositoryMongoDBImpl.class);

    @Override
    public void create(Passenger passenger, Long passportId) {
        LOGGER.info("Passenger was added to the database.");
    }

    @Override
    public void deleteById(Long passengerId) {
        LOGGER.info("Passenger was deleted from the database.");
    }

    @Override
    public Passenger findById(Long passengerId) {
        return null;
    }

    @Override
    public Long getPassportId(Long passengerId) {
        return null;
    }

    @Override
    public List<Passenger> findAll() {
        return null;
    }

    @Override
    public void updateContactInfoById(String phoneNumber, String email, Long passengerId) {
        LOGGER.info("Contact information for the passenger was updated");
    }
}
