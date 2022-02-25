package com.urise.webapp.model;

import java.util.Objects;

public class SimpleLineSections extends Section {

    protected String personalData;

    protected SimpleLineSections(String item) {
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
}
