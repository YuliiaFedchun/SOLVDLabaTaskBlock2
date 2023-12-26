package com.laba.solvd.persistence;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private static volatile List<Connection> connections;
    private static final Integer MAX_CONNECTION_NUMBER = Config.poolSize;

    private ConnectionPool() {
       /* try {
            Class.forName(Config.driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find a class", e);
        }*/
        try {
            DriverManager.registerDriver(new Driver());
        } catch (Exception e) {
            throw new RuntimeException("Can't register driver", e);
        }
        connections = new ArrayList<>();
        IntStream.range(0, MAX_CONNECTION_NUMBER)
                .boxed()
                .forEach(i -> connections.add(createConnection()));
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                try {
                    Class.forName(Config.driver);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Can't find a class", e);
                }
                instance = new ConnectionPool();
            }
        }
        return instance;
    }

    private static Connection createConnection() {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (Exception e) {
            throw new RuntimeException("Can't register driver", e);
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(Config.url,
                    Config.userName, Config.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public Connection getConnection() {
        return connections.remove(0);
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }


}
