package com.epam.jwd.training.entity;

public enum UserRole {
    ADMIN,
    USER;

    public static UserRole resolveUserRoleByName(String name) {
        for (UserRole role : values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        return USER;
    }
}
