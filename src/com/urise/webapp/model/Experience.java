package com.urise.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Objects;

public class Experience implements Serializable {
    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String position;
    private final String description;

    public Experience(YearMonth startDate, YearMonth endDate, String position, String description) {
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
        Experience that = (Experience) o;
        return startDate.equals(that.startDate) && endDate.equals(that.endDate)
                && position.equals(that.position) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, position, description);
    }
}
