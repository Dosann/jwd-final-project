package com.epam.jwd.training.service;

import com.epam.jwd.training.dao.CommonDao;
import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.entity.dto.CourseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService implements CommonService<CourseDto> {

    private final CommonDao<Course> courseDao;

    public CourseService(CommonDao<Course> courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Optional<List<CourseDto>> findAll() {
        return courseDao.findAll()
                .map(
                        courses -> courses.stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList())
                );
    }

    @Override
    public Optional<CourseDto> save(CourseDto dto) {
        return Optional.empty();
    }

    //todo: need Course instead of CourseDto
    @Override
    public boolean create(CourseDto entity) {
        courseDao.create(entity);
    }

    private CourseDto convertToDto(Course course) {
        return new CourseDto(course.getName(), course.getDescription(), course.getStartDate());
    }

    //todo: pattern Mapper?
    private Course convertFromDto(CourseDto courseDto) {
        return new Course()
    }

}
