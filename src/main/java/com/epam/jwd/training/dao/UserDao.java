package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.User;
import com.epam.jwd.training.entity.UserRole;
import com.epam.jwd.training.pool.ConnectionPool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements CommonDao<User>{

//    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    // todo: что-то связанное с SQL
    private static final String SELECT_ALL_USERS = "SELECT id, u_login as login, u_password as password, " +
            "u_name as name, u_email as email, u_role_id as role FROM tr_user";

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public Optional<List<User>> findAll() {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                //todo: user factory and constants: id, password etc
                User user = new User(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getString("name"),
                        resultSet.getString("email"), UserRole.resolveUserRoleByName("role"));
                users.add(user);
            }
            return Optional.of(users);
        } catch (SQLException e) {
//            LOGGER.error("Exception  has occurred: {}", e.toString());
            return Optional.empty();
        }
    }
}
