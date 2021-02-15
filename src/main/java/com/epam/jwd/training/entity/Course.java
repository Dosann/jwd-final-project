package com.epam.jwd.training.entity;

import java.time.LocalDateTime;

public class Course implements BaseEntity {

    private int id;
    private String name;
    private String description;
    private LocalDateTime startDate;

    public Course(int id, String name, String description, LocalDateTime startDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                "name=" + name +
                ", description=" + description +
                ", startDate=" + startDate +
                '}';
    }
}
