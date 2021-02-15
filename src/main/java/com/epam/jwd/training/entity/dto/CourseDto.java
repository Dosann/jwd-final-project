package com.epam.jwd.training.entity.dto;

import com.epam.jwd.training.entity.BaseEntity;

import java.time.LocalDateTime;

public class CourseDto implements BaseEntity {

    private final String name;
    private final String description;
    private final LocalDateTime startDate;

    public CourseDto(String name, String description, LocalDateTime startDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
