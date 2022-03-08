package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListOrganization extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<Organization> organizations;

    public ListOrganization(List<Organization> data) {
        Objects.requireNonNull(data, "item must not be null");
        this.organizations = data;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Organization listItem : organizations) {
            result.append(listItem).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOrganization that = (ListOrganization) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
