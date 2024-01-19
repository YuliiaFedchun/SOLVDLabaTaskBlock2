package com.laba.solvd.persistence.repository;

public interface RepositoriesFactory {
    PassportRepository createPassportRepository(String type);

    PassengerRepository createPassengerRepository(String type);
}
