package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;
    
    private  List<String> personalData;

    public ListSection() {
    }

    public ListSection(List<String> data) {
        Objects.requireNonNull(data, "item must not be null");
        this.personalData = data;
    }

    public List<String> getPersonalData() {
        return personalData;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (String listItem : personalData) {
            result.append(listItem).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(personalData, that.personalData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData);
    }
}
