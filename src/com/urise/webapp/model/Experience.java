package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Experience {

    private final String name;
    private final String url;
    private final List<ExperienceDate> experienceDate;

    public Experience(String name, String url, List<ExperienceDate> experienceDate) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(experienceDate, "experience date must not be null");
        this.name = name;
        this.url = url;
        this.experienceDate = experienceDate;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(name).append(" ").append(url).append(System.lineSeparator());
        for (ExperienceDate data : experienceDate) {
            result.append(data).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return name.equals(that.name) && Objects.equals(url, that.url) && experienceDate.equals(that.experienceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, experienceDate);
    }
}