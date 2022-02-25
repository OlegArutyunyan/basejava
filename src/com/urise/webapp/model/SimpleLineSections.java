package com.urise.webapp.model;

import java.util.Objects;

public class SimpleLineSections extends AbstractSection {

    private final String personalData;

    public SimpleLineSections(String item) {
        Objects.requireNonNull(item, "item must not be null");
        this.personalData = item;
    }

    protected String getItem() {
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
        SimpleLineSections that = (SimpleLineSections) o;
        return Objects.equals(personalData, that.personalData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData);
    }
}
