package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class Experience {

    private final String name;
    private final String url;
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String position;
    private final String description;

    public Experience(String name, String url, YearMonth startDate, YearMonth endDate, String position, String description) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(url, "name must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(position, "position must not be null");
        Objects.requireNonNull(description, "description must not be null");
        this.name = name;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.description = description;
    }


    @Override
    public String toString() {
        return name + " " + url + System.lineSeparator() +
                startDate + " " + endDate + " " + position + System.lineSeparator() +
                description + System.lineSeparator();
    }
}
