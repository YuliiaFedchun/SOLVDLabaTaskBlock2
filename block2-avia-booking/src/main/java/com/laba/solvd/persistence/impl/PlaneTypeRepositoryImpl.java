package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.PlaneTypeRepository;

import java.sql.*;

public class PlaneTypeRepositoryImpl implements PlaneTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(PlaneType planeType) {

        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "Insert into plane_types (name, seats_number) values (?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, planeType.getName());
            preparedStatement.setInt(2, planeType.getSeatsNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                planeType.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
