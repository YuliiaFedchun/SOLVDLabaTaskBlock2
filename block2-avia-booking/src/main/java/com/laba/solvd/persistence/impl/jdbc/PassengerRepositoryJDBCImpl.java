package com.laba.solvd.persistence.impl.jdbc;

import com.laba.solvd.domain.Passenger;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.PassengerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepositoryJDBCImpl implements PassengerRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Passenger passenger, Long passportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "Insert into passengers (first_name, last_name, age, phone_number, email, " +
                        "passport_id) values (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, passenger.getFirstName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setInt(3, passenger.getAge());
            preparedStatement.setString(4, passenger.getPhoneNumber());
            preparedStatement.setString(5, passenger.getEmail());
            preparedStatement.setLong(6, passportId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passenger.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public void deleteById(Long passengerId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String deleteById =
                "DELETE FROM passengers WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(deleteById);
            preparedStatement.setLong(1, passengerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public Passenger findById(Long passengerId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Passenger passenger;
        String selectById =
                "SELECT id, first_name, last_name, age, phone_number, email " +
                        "FROM passengers WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, passengerId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            passenger = mapPassengers(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return passenger;
    }

    public Long getPassportId(Long passengerId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long passportId = null;
        String selectById =
                "SELECT passport_id FROM passengers WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, passengerId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                passportId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return passportId;
    }

    @Override
    public List<Passenger> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Passenger> passengers;
        String selectAll =
                "SELECT * FROM passengers";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectAll);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            passengers = mapPassengers(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return passengers;
    }

    @Override
    public void updateContactInfoById(String phoneNumber, String email,
                                      Long passengerId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String updateContactInfo = "UPDATE passengers " +
                "SET phone_number = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(updateContactInfo);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, email);
            preparedStatement.setLong(3, passengerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private List<Passenger> mapPassengers(ResultSet resultSet) {
        List<Passenger> passengers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong(1));
                passenger.setFirstName(resultSet.getString(2));
                passenger.setLastName(resultSet.getString(3));
                passenger.setAge(resultSet.getInt(4));
                passenger.setPhoneNumber(resultSet.getString(5));
                passenger.setEmail(resultSet.getString(6));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengers;
    }
}
