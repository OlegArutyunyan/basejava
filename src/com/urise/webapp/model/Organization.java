package com.urise.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String url;
    private final List<Experience> experience;

    public Organization(String name, String url, List<Experience> experience) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(experience, "experience date must not be null");
        this.name = name;
        this.url = url;
        this.experience = experience;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(name).append(" ").append(url).append(System.lineSeparator());
        for (Experience data : experience) {
            result.append(data).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name) && Objects.equals(url, that.url) && experience.equals(that.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, experience);
    }
}