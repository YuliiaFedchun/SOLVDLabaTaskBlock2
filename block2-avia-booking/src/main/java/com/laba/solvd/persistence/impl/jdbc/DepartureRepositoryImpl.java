package com.laba.solvd.persistence.impl.jdbc;

import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.DepartureRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    /*@Override
    public Long getDepartureId(Long airportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long departureId = null;
        String selectById =
                "SELECT id FROM departures WHERE airport_id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, airportId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                departureId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return departureId;
    }*/
}
