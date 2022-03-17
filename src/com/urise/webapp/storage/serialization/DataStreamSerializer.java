package com.urise.webapp.storage.serialization;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();

            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeWithException(r.getSections().entrySet(), dos, entry -> {
                SectionType key = entry.getKey();

                dos.writeUTF(key.name());
                switch (key) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) entry.getValue()).getItem());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> personalData = ((ListSection) entry.getValue()).getPersonalData();

                        writeWithException(personalData, dos, dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = ((OrganizationSection) entry.getValue()).getOrganizations();

                        writeWithException(organizations, dos, organization -> {
                            writeHomePage(dos, organization.getHomePage());

                            writeWithException(organization.getPosition(), dos, position -> {
                                writePosition(dos, position);
                            });
                        });
                    }
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());

                switch (type) {
                    case PERSONAL, OBJECTIVE -> resume.setSection(type, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> personalData = new ArrayList<>();
                        int personalDataSize = dis.readInt();

                        for (int j = 0; j < personalDataSize; j++) {
                            personalData.add(dis.readUTF());
                        }
                        resume.setSection(type, new ListSection(personalData));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = new ArrayList<>();
                        int organizationsSize = dis.readInt();

                        for (int j = 0; j < organizationsSize; j++) {
                            String organizationName = dis.readUTF();
                            String organizationUrl = dis.readUTF();

                            List<Organization.Position> positions = new ArrayList<>();
                            int positionsSize = dis.readInt();

                            for (int k = 0; k < positionsSize; k++) {
                                positions.add(new Organization.Position(stringToDate(dis),
                                        stringToDate(dis), dis.readUTF(), dis.readUTF()));
                            }
                            organizations.add(new Organization(organizationName, organizationUrl, positions));
                        }
                        resume.setSection(type, new OrganizationSection(organizations));
                    }
                }
            }
            return resume;
        }
    }

    private void writeHomePage(DataOutputStream dos, Link homePage) throws IOException {
        dos.writeUTF(homePage.getName());
        dos.writeUTF(homePage.getUrl());
    }

    private void writePosition(DataOutputStream dos, Organization.Position position) throws IOException {
        dateToString(position.getStartDate(), dos);
        dateToString(position.getEndDate(), dos);
        dos.writeUTF(position.getPosition());
        dos.writeUTF(position.getDescription());
    }

    private void dateToString(YearMonth ym, DataOutputStream dos) throws IOException {
        dos.writeInt(ym.getYear());
        dos.writeInt(ym.getMonthValue());
    }

    private YearMonth stringToDate(DataInputStream dis) throws IOException {
        return YearMonth.of(dis.readInt(), dis.readInt());
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, Writer<T> writer)
            throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }

    interface Writer<T> {
        void write(T t) throws IOException;
    }
}
