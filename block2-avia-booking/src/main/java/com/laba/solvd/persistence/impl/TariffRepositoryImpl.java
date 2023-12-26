package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Tariff;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.TariffRepository;

import java.sql.*;

public class TariffRepositoryImpl implements TariffRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Tariff tariff, Long airlineId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO tariffs (name, hand_luggage, register_luggage, place_choice, fast_track, " +
                        "priority_boarding, airline_id, service_class_id, base_price) " +
                        "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tariff.getName());
            preparedStatement.setInt(2, tariff.getHandLuggage());
            preparedStatement.setInt(3, tariff.getRegisterLuggage());
            preparedStatement.setBoolean(4, tariff.hasPlaceChoice());
            preparedStatement.setBoolean(5, tariff.hasFastTrack());
            preparedStatement.setBoolean(6, tariff.hasPriorityBoarding());
            preparedStatement.setLong(7, airlineId);
            preparedStatement.setLong(8, tariff.getServiceClass().getId());
            preparedStatement.setDouble(9, tariff.getBasePrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                tariff.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
