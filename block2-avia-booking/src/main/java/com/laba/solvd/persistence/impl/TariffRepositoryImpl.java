package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Tariff;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.TariffRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffRepositoryImpl implements TariffRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Tariff tariff, Long airlineId, Long serviceClassId) {
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
            preparedStatement.setLong(8, serviceClassId);
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

    @Override
    public List<Tariff> findAllByAirlineId(Long airlineId) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Tariff> tariffs;
        String selectByAirlineId =
                "SELECT id, name, hand_luggage, register_luggage, place_choice, fast_track, " +
                        "priority_boarding, base_price FROM tariffs WHERE airline_id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectByAirlineId);
            preparedStatement.setLong(1, airlineId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            tariffs = mapTariff(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return tariffs;
    }

    @Override
    public Long getServiceClassId(Long tariffId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long serviceClassId = null;
        String selectById =
                "SELECT service_class_id FROM tariffs WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, tariffId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                serviceClassId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return serviceClassId;
    }


    private List<Tariff> mapTariff(ResultSet resultSet) {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setId(resultSet.getLong(1));
                tariff.setName(resultSet.getString(2));
                tariff.setHandLuggage(resultSet.getInt(3));
                tariff.setRegisterLuggage(resultSet.getInt(4));
                tariff.setPlaceChoice(resultSet.getBoolean(5));
                tariff.setFastTrack(resultSet.getBoolean(6));
                tariff.setPriorityBoarding(resultSet.getBoolean(7));
                tariff.setBasePrice(resultSet.getDouble(8));
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tariffs;
    }

}
