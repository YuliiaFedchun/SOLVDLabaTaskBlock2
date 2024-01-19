package com.laba.solvd.persistence.impl.mongodb;

import com.laba.solvd.domain.Passport;
import com.laba.solvd.persistence.repository.PassportRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PassportRepositoryMongoDBImpl implements PassportRepository {
    private static final Logger LOGGER = LogManager.getLogger(PassportRepositoryMongoDBImpl.class);

    @Override
    public void create(Passport passport) {
        LOGGER.info("Passport was added to the database.");
    }

    @Override
    public Passport findById(Long passportId) {
        return null;
    }

    @Override
    public void deleteById(Long passportId) {
        LOGGER.info("Passport was deleted from the database.");
    }
}
