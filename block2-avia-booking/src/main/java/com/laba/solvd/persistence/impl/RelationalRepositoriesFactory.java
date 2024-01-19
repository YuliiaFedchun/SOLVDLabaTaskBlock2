package com.laba.solvd.persistence.impl;

import com.laba.solvd.persistence.impl.jdbc.PassengerRepositoryJDBCImpl;
import com.laba.solvd.persistence.impl.jdbc.PassportRepositoryJDBCImpl;
import com.laba.solvd.persistence.impl.mybatis.PassengerRepositoryMybatisImpl;
import com.laba.solvd.persistence.impl.mybatis.PassportRepositoryMybatisImpl;
import com.laba.solvd.persistence.repository.PassengerRepository;
import com.laba.solvd.persistence.repository.PassportRepository;
import com.laba.solvd.persistence.repository.RepositoriesFactory;

public class RelationalRepositoriesFactory implements RepositoriesFactory {
    @Override
    public PassportRepository createPassportRepository(String type) {
        PassportRepository repository;
        switch (type) {
            case "JDBC":
                repository = new PassportRepositoryJDBCImpl();
                break;
            case "MYBATIS":
                repository = new PassportRepositoryMybatisImpl();
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
            case "JDBC":
                repository = new PassengerRepositoryJDBCImpl();
                break;
            case "MYBATIS":
                repository = new PassengerRepositoryMybatisImpl();
                break;
            default:
                throw new RuntimeException(String.format("Can't create repository of '%s' type", type));
        }
        return repository;
    }
}
