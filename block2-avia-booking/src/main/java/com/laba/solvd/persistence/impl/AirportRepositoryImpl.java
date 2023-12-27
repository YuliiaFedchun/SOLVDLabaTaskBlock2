package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Airport;
import com.laba.solvd.domain.Passenger;
import com.laba.solvd.domain.enums.AirportType;
import com.laba.solvd.persistence.repository.AirportRepository;
import com.laba.solvd.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportRepositoryImpl implements AirportRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Airport airport) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO airports (name, city, country, airport_type_id) " +
                        "VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getCity());
            preparedStatement.setString(3, airport.getCountry());
            preparedStatement.setLong(4, airport.getTypeName().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                airport.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public Airport findById(Long airportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Airport airport;
        String selectById =
                "SELECT id, name, city, country, airport_type_id FROM airports WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, airportId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            airport = mapAirports(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return airport;
    }

    @Override
    public Long findIdByName(String name) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long airportId = null;
        String selectById =
                "SELECT id FROM airports WHERE name = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setString(1, name);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) {
                airportId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return airportId;
    }

    private List<Airport> mapAirports(ResultSet resultSet) {
        List<Airport> airports = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport.setId(resultSet.getLong(1));
                airport.setName(resultSet.getString(2));
                airport.setCity(resultSet.getString(3));
                airport.setCountry(resultSet.getString(4));
                Long airportTypeId = resultSet.getLong(5);
                for (AirportType airportType : AirportType.values()) {
                    if(airportType.getId().equals(airportTypeId)) {
                        airport.setTypeName(airportType);
                    }
                }

                airports.add(airport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return airports;
    }
}
