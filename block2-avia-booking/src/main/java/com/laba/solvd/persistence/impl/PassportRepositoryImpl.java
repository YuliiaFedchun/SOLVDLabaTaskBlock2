package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Passport;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.PassportRepository;

import java.sql.*;

public class PassportRepositoryImpl implements PassportRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Passport passport) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "Insert into passports (number) values(?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, passport.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passport.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't create a passport", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public Passport findById(Long passportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Passport passport = new Passport();
        String selectById =
                "SELECT id, number FROM passports WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, passportId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                passport.setId(resultSet.getLong(1));
                passport.setNumber(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return passport;
    }

    @Override
    public void deleteById(Long passportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String deleteById =
                "DELETE FROM passports WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(deleteById);
            preparedStatement.setLong(1, passportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
