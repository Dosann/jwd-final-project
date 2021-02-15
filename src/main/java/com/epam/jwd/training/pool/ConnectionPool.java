package com.epam.jwd.training.pool;

import com.epam.jwd.training.entity.ApplicationProperties;
import com.epam.jwd.training.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;

    private static final Lock LOCK = new ReentrantLock();
    private static final Condition NOT_FULL  = LOCK.newCondition();
    private static final Condition NOT_EMPTY = LOCK.newCondition();

//    private static final ApplicationProperties PROPERTIES = PropertyReaderUtil.populateApplicationProperties();
//    private static final int INITIAL_POOL_SIZE = PROPERTIES.getInitPoolSize();
//    private static final int MAX_POOL_SIZE = PROPERTIES.getMaxPoolSize();
//    private static final int INCREMENTAL_POOL_SIZE = PROPERTIES.getIncrementalPoolSize();

    //todo: remove this
    private static final int INITIAL_POOL_SIZE = 8;
    private static final int MAX_POOL_SIZE = 32;
    private static final int INCREMENTAL_POOL_SIZE = 4;
    //

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> takenConnections;

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>(INITIAL_POOL_SIZE);
        takenConnections = new ArrayDeque<>();
        init(INITIAL_POOL_SIZE);
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    public void init(int amountOfConnections) {
        registerDrivers();
        try {
            for (int i = 0; i < amountOfConnections; i++) {
//                Connection realConnection = DriverManager.getConnection(PROPERTIES.getUrl(), PROPERTIES.getUsername(),
//                            PROPERTIES.getPassword());
                Connection realConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineTraining",
                        "root", "Book0378");
                final ProxyConnection proxyConnection = new ProxyConnection(realConnection);
                availableConnections.add(proxyConnection);
            }
        } catch(SQLException e) {
            LOGGER.error("SQL exception has occurred {}", e.toString());
            //todo: custom exception
           throw new IllegalStateException(e);
        }
    }
    
    public void returnConnection(ProxyConnection connection) {
        LOCK.lock();
        try {
            while(takenConnections.isEmpty()) {
                NOT_EMPTY.await();
            }
            if (takenConnections.contains(connection)) {
                takenConnections.remove(connection);
                availableConnections.offer(connection);
                NOT_FULL.signal();
            }
        } catch(InterruptedException e) {
            LOGGER.error("Exception has occurred while returning connection into pool, {}", e.toString());
        } finally {
            LOCK.unlock();
        }
    }

    public Connection retrieveConnection() {
        LOCK.lock();
        try {
            if (availableConnections.isEmpty() && takenConnections.size() < MAX_POOL_SIZE) {
                init(INCREMENTAL_POOL_SIZE);
            }
            ProxyConnection connection = availableConnections.remove();
            takenConnections.add(connection);
            return connection;
        } finally {
            LOCK.unlock();
        }
    }

    public void destroyConnections() {
        LOCK.lock();
        try {
            availableConnections.forEach(ProxyConnection::closeConnection);
            takenConnections.forEach(ProxyConnection::closeConnection);
            deregisterDrivers();
        } finally {
            LOCK.unlock();
        }
    }

    private static void registerDrivers() {
        try {
//            new com.mysql.cj.jdbc.Driver();
            Class.forName("com.mysql.cj.jdbc.Driver");
//            DriverManager.registerDriver(DriverManager.getDriver(PROPERTIES.getUrl()));
            DriverManager.registerDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/onlineTraining"));
        } catch (SQLException | ClassNotFoundException e) {
           LOGGER.error("Registering drivers was unsuccessful: {}", e.toString());
        }
    }

    private static void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error("Deregistering drivers was unsuccessful: {}", e.toString());
            }
        }
    }
}
