package com.laba.solvd.persistence.impl.jdbc;

import com.laba.solvd.domain.LuggageTariff;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.LuggageTariffRepository;

import java.sql.*;

public class LuggageTariffRepositoryImpl implements LuggageTariffRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(LuggageTariff luggageTariff) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO luggage_tariffs (hand_luggage_price, register_luggage_price) " +
                        "VALUES (?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, luggageTariff.getHandLuggagePrice());
            preparedStatement.setDouble(2, luggageTariff.getRegisterLuggagePrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                luggageTariff.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
