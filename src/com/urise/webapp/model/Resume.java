package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume{

    // Unique identifier
    private final String uuid;

    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullname must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public String getContacts(ContactType type) {
        return contacts.get(type);
    }

    public Object getSections(SectionType type) {
        return sections.get(type);
    }

    public void setContacts(ContactType type, String value) {
        contacts.put(type, value);
    }

    public void setSections(SectionType type, Section value) {
        sections.put(type, value);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }
}
