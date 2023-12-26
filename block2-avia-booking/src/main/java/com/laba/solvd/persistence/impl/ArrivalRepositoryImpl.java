package com.laba.solvd.persistence.impl;

import com.laba.solvd.persistence.repository.ArrivalRepository;
import com.laba.solvd.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArrivalRepositoryImpl implements ArrivalRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long airportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO arrivals (airport_id) VALUES (?)";
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
