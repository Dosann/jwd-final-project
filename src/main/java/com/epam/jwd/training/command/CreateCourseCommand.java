package com.epam.jwd.training.command;

import com.epam.jwd.training.dao.CourseDao;
import com.epam.jwd.training.entity.BaseEntity;
import com.epam.jwd.training.entity.Course;
import com.epam.jwd.training.entity.dto.CourseDto;
import com.epam.jwd.training.service.CommonService;
import com.epam.jwd.training.service.CourseService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public enum CreateCourseCommand implements Command {
    INSTANCE;

    private static final ResponseContext COURSES_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/courses.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final String COURSES_ATTRIBUTE_NAME = "createCourse";

    private final CommonService<CourseDto> courseService;

    CreateCourseCommand() {
        courseService = new CourseService(CourseDao.INSTANCE);
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        String name = (String) request.getAttribute("name");
        String description = (String) request.getAttribute("description");
        LocalDateTime startDate = (LocalDateTime) request.getAttribute("startDate");
        BaseEntity newCourse = new CourseDto(name, description, startDate);
        courseService.
        response.sendRedirect("list");


        final List<CourseDto> courses = courseService.findAll().orElse(Collections.emptyList());
        request.setAttribute(COURSES_ATTRIBUTE_NAME, courses);
        return COURSES_PAGE_RESPONSE;
    }
}
