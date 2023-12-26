package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Airport;
import com.laba.solvd.persistence.repository.AirportRepository;
import com.laba.solvd.persistence.ConnectionPool;

import java.sql.*;

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
}
