package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Flight;
import com.laba.solvd.domain.Tariff;
import com.laba.solvd.domain.enums.ServiceClass;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.TariffRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Tariff> findAllByAirlineId(Long airlineId) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Tariff> tariffs;
        String selectByDepartureId =
                "SELECT id, name, hand_luggage, register_luggage, place_choice, fast_track, " +
                        "priority_boarding, service_class_id, base_price " +
                        "FROM tariffs WHERE airline_id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectByDepartureId);
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
                tariff.setBasePrice(resultSet.getDouble(9));
                Long serviceClassId = resultSet.getLong(8);
                for(ServiceClass serviceClass : ServiceClass.values()) {
                    if(serviceClass.getId().equals(serviceClassId)) {
                        tariff.setServiceClass(serviceClass);
                    }
                }
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tariffs;
    }
}
