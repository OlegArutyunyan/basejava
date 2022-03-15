package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends Section {
    private static final long serialVersionUID = 1L;

    private String personalData;

    public TextSection() {
    }

    public TextSection(String item) {
        Objects.requireNonNull(item, "item must not be null");
        this.personalData = item;
    }

    public String getItem() {
        return personalData;
    }

    @Override
    public String toString() {
        return personalData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(personalData, that.personalData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData);
    }
}
