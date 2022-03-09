package com.urise.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Link homePage;
    private final List<Position> position;

    public Organization(String name, String url, List<Position> position) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(position, "experience date must not be null");
        this.homePage = new Link(name, url);
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, position);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(homePage).append(System.lineSeparator());
        for (Position data : position) {
            result.append(data).append(System.lineSeparator());
        }
        return result.toString();
    }

    public static class Position implements Serializable {
        private final YearMonth startDate;
        private final YearMonth endDate;
        private final String position;
        private final String description;

        public Position(YearMonth startDate, YearMonth endDate, String position, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(position, "position must not be null");
            Objects.requireNonNull(description, "description must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.description = description;
        }


        @Override
        public String toString() {
            return startDate + " " + endDate + " " + position + System.lineSeparator() + description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position that = (Position) o;
            return startDate.equals(that.startDate) && endDate.equals(that.endDate)
                    && position.equals(that.position) && description.equals(that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, position, description);
        }
    }
}