package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSections extends Section{
    protected List<String> personalData;

    public ListSections(List<String> data) {
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
}
