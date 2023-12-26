package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.Passenger;
import com.laba.solvd.persistence.repository.AirlineRepository;
import com.laba.solvd.persistence.ConnectionPool;

import java.sql.*;

public class AirlineRepositoryImpl implements AirlineRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Airline airline) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO airlines (name, code, luggage_tariff_id) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, airline.getName());
            preparedStatement.setString(2, airline.getCode());
            preparedStatement.setLong(3, airline.getLuggageTariff().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                airline.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public Airline findById(Long airlineId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Airline airline;
        String selectById =
                "SELECT id, name, code FROM airlines WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, airlineId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            airline = mapAirline(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return airline;
    }

    private Airline mapAirline(ResultSet resultSet) {
        Airline airline = new Airline();
        try {
            while (resultSet.next()) {
                airline.setId(resultSet.getLong(1));
                airline.setName(resultSet.getString(2));
                airline.setCode(resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return airline;
    }
}
