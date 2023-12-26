package com.laba.solvd.persistence.impl;

import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.AirlineHasFlightTypeRepository;

import java.sql.*;

public class AirlineHasFlightTypeRepositoryImpl implements AirlineHasFlightTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long airlineId, Long flightTypeId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO airlines_has_flight_types (airline_id, flight_type_id) " +
                        "VALUES (?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto);
            preparedStatement.setLong(1, airlineId);
            preparedStatement.setLong(2, flightTypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }
}
