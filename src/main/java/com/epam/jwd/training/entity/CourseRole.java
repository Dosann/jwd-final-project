package com.epam.jwd.training.entity;

public enum CourseRole {
    MENTOR,
    STUDENT;

    public static CourseRole resolveCourseRoleByName(String name) {
        for (CourseRole role : values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        return STUDENT;
    }
}
