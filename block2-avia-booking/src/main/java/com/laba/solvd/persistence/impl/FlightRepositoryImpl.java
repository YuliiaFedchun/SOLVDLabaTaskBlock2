package com.laba.solvd.persistence.impl;

import com.laba.solvd.domain.Flight;
import com.laba.solvd.persistence.ConnectionPool;
import com.laba.solvd.persistence.repository.FlightRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Flight flight, Long airlineId, Long departureId, Long arrivalId,
                       Long planeTypeId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto =
                "INSERT INTO flights (number, departure_time, arrival_time, airline_id, " +
                        "departure_id, arrival_id, plane_type_id) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, flight.getNumber());
            preparedStatement.setString(2, flight.getDepartureTime());
            preparedStatement.setString(3, flight.getArrivalTime());
            preparedStatement.setLong(4, airlineId);
            preparedStatement.setLong(5, departureId);
            preparedStatement.setLong(6, arrivalId);
            preparedStatement.setLong(7, planeTypeId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                flight.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Flight findById(Long flightId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Flight flight;
        String selectById =
                "SELECT id, number, departure_time, arrival_time " +
                        "FROM flights WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, flightId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            flight = mapFlight(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return flight;
    }

    @Override
    public List<Flight> findByDepartureId(Long departureId) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Flight> flights;
        String selectByDepartureId =
                "SELECT id, number, departure_time, arrival_time " +
                        "FROM flights WHERE departure_id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectByDepartureId);
            preparedStatement.setLong(1, departureId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            flights = mapFlight(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return flights;
    }

    @Override
    public Long getAirlineId(Long flightId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long airlineId = null;
        String selectById =
                "SELECT airline_id FROM flights WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, flightId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                airlineId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return airlineId;
    }

    @Override
    public Long getDepartureId(Long flightId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long departureId = null;
        String selectById =
                "SELECT departure_id FROM flights WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, flightId);
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
    }

    @Override
    public Long getArrivalId(Long flightId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long arrivalId = null;
        String selectById =
                "SELECT arrival_id FROM flights WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, flightId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                arrivalId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return arrivalId;
    }

    @Override
    public Long getPlaneTypeId(Long flightId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Long PlaneTypeId = null;
        String selectById =
                "SELECT plane_type_id FROM flights WHERE id = ?";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectById);
            preparedStatement.setLong(1, flightId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                PlaneTypeId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return PlaneTypeId;
    }

    private List<Flight> mapFlight(ResultSet resultSet) {
        List<Flight> flights = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getLong(1));
                flight.setNumber(resultSet.getString(2));
                flight.setDepartureTime(resultSet.getString(3));
                flight.setArrivalTime(resultSet.getString(4));
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }
}
