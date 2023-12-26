package com.laba.solvd.persistence.impl;

import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.DepartureRepository;

import java.sql.*;

public class DepartureRepositoryImpl implements DepartureRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long airportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO departures (airport_id) VALUES (?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto);
            preparedStatement.setLong(1, airportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }
}
