package com.laba.solvd.persistence.impl.jdbc;

import com.laba.solvd.domain.Ticket;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.TicketRepository;

import java.sql.*;

public class TicketRepositoryImpl implements TicketRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Ticket ticket, Long passengerId, Long flightId, Long tariffId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "Insert into tickets (passenger_id, flight_id, add_hand_luggage, " +
                        "add_register_luggage, tariff_id, price) values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, passengerId);
            preparedStatement.setLong(2, flightId);
            preparedStatement.setInt(3, ticket.getAddHandLuggage());
            preparedStatement.setInt(4, ticket.getAddRegisterLuggage());
            preparedStatement.setLong(5, tariffId);
            preparedStatement.setDouble(6, ticket.getPrice());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                ticket.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public void deleteByPassengerId(Long passengerId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String deleteByPassengerId =
                "DELETE FROM tickets WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(deleteByPassengerId);
            preparedStatement.setLong(1, passengerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }
}
