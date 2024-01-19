package com.laba.solvd.persistence.impl;

import com.laba.solvd.persistence.impl.mongodb.PassengerRepositoryMongoDBImpl;
import com.laba.solvd.persistence.impl.mongodb.PassportRepositoryMongoDBImpl;
import com.laba.solvd.persistence.repository.PassengerRepository;
import com.laba.solvd.persistence.repository.PassportRepository;
import com.laba.solvd.persistence.repository.RepositoriesFactory;

public class NoSQLRepositoriesFactory implements RepositoriesFactory {
    @Override
    public PassportRepository createPassportRepository(String type) {
        PassportRepository repository;
        switch (type) {
            case "MONGODB":
                repository = new PassportRepositoryMongoDBImpl();
                break;
            default:
                throw new RuntimeException(String.format("Can't create repository of '%s' type", type));
        }
        return repository;
    }

    @Override
    public PassengerRepository createPassengerRepository(String type) {
        PassengerRepository repository;
        switch (type) {
            case "MONGODB":
                repository = new PassengerRepositoryMongoDBImpl();
                break;
            default:
                throw new RuntimeException(String.format("Can't create repository of '%s' type", type));
        }
        return repository;
    }
}
