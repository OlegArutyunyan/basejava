package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    private final List<Experience> organizations;

    public Organization(List<Experience> data) {
        Objects.requireNonNull(data, "item must not be null");
        this.organizations = data;
    }

    public List<Experience> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Experience listItem : organizations) {
            result.append(listItem).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
