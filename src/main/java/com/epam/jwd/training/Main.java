package com.epam.jwd.training;

import com.epam.jwd.training.entity.ApplicationProperties;
import com.epam.jwd.training.entity.Course;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Properties;

public class Main {

    private static final String SELECT_SQL = "SELECT course.id, c_name as name, c_description as description, " +
            "c_start_date as startDate FROM course";

    private static final Properties properties = new Properties();
    private static void loadProperties() {
//        LOGGER.info("Loading properties from file");

        final String propertiesFileName = "src/main/resources/application.properties";
        try(FileInputStream iStream = new FileInputStream(propertiesFileName)) {
            properties.load(iStream);
        } catch (IOException e) {
//            LOGGER.error("Exception has occurred: {}", e.toString());
        }
    }

    public static ApplicationProperties populateApplicationProperties() {
//        LOGGER.info("Populating application properties");

        loadProperties();
        return new ApplicationProperties(properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"),
                Integer.parseInt(properties.getProperty("initPoolSize")),
                Integer.parseInt(properties.getProperty("maxPoolSize")),
                Integer.parseInt(properties.getProperty("incrementalPoolSize")));
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        ApplicationProperties applicationProperties = populateApplicationProperties();

            registerDrivers();
            try (final Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/onlineTraining", "root", "Book0378");
                 final Statement statement = conn.createStatement();
                 final ResultSet resultSet = statement.executeQuery(SELECT_SQL)) {
                while (resultSet.next()) {
                    final Course user = new Course(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            LocalDateTime.parse(resultSet.getString("startDate").replace(' ', 'T')));
                    System.out.println("course read successfully");
                    System.out.println(user);
                }
            } catch (SQLException e) {
                System.out.println("course read unsuccessfully");
                e.printStackTrace();
            }
            deregisterDrivers();
        }

        private static void registerDrivers() {
            System.out.println("registering another drivers");
            try {
                DriverManager.registerDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/onlineTraining"));
                System.out.println("registration successful");
            } catch (SQLException e) {
                System.out.println("deregistering successful");
                e.printStackTrace();
            }
        }
            private static void deregisterDrivers() {
                Enumeration<Driver> drivers = DriverManager.getDrivers();
                while (drivers.hasMoreElements()) {
                    try {
                        DriverManager.deregisterDriver(drivers.nextElement());
                    } catch (SQLException e) {
                        System.out.println("deregistration unsuccessful");
                        e.printStackTrace();
                    }
                }
            }
        }

