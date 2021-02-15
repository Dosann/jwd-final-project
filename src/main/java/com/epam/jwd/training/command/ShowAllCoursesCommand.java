package com.epam.jwd.training.command;

import com.epam.jwd.training.dao.CourseDao;
import com.epam.jwd.training.entity.dto.CourseDto;
import com.epam.jwd.training.service.CommonService;
import com.epam.jwd.training.service.CourseService;

import java.util.Collections;
import java.util.List;

public enum ShowAllCoursesCommand implements Command {
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

    private static final String COURSES_ATTRIBUTE_NAME = "listCourse";

    private final CommonService<CourseDto> courseService;

    ShowAllCoursesCommand() {
        courseService = new CourseService(CourseDao.INSTANCE);
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        final List<CourseDto> courses = courseService.findAll().orElse(Collections.emptyList());
        request.setAttribute(COURSES_ATTRIBUTE_NAME, courses);
        return COURSES_PAGE_RESPONSE;
    }
}
