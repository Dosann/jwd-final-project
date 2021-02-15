package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//todo: remove enum and INSTANCE
public enum CourseDao implements CommonDao<Course> {
    INSTANCE;

    private final static String FIND_ALL_COURSES_SQL = "SELECT course.id, c_name, c_description, c_start_date " +
            "FROM course";

    private final String CREATE_NEW_COURSE_SQL = "INSERT INTO course (c_name, c_description, c_start_date) " +
            "VALUES (?, ?, ?)";

    private static final String ID_COLUMN_NAME = "id";
    private static final String COURSE_NAME_COLUMN_NAME = "c_name";
    private static final String COURSE_DESCRIPTION_COLUMN_NAME = "c_description";
    private static final String COURSE_START_DATE_COLUMN_NAME = "c_start_date";

    @Override
    public Optional<List<Course>> findAll() {
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_COURSES_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(readCourse(resultSet));
            }
            return Optional.of(courses);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean create(Course course) {
        try (final Connection connection = ConnectionPool.getInstance().retrieveConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_COURSE_SQL)) {
            preparedStatement.setString(1, String.valueOf(course.getId()));
            preparedStatement.setString(2, course.getName());
            preparedStatement.setString(3, course.getDescription());
            preparedStatement.setString(4, course.getStartDate().toString());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }

        private Course readCourse(ResultSet resultSet) throws SQLException {
        return new Course(resultSet.getInt(ID_COLUMN_NAME),
                resultSet.getString(COURSE_NAME_COLUMN_NAME),
                resultSet.getString(COURSE_DESCRIPTION_COLUMN_NAME),
                LocalDateTime.parse(resultSet.getString(COURSE_START_DATE_COLUMN_NAME).replace(' ', 'T')));
    }
}
