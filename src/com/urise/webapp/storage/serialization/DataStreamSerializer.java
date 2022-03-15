package com.urise.webapp.storage.serialization;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serialization {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            dos.writeInt((r.getSections().size()));
            for (Map.Entry<SectionType, Section> entry : r.getSections().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) entry.getValue()).getItem());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> personalData = ((ListSection) entry.getValue()).getPersonalData();
                        dos.writeInt(personalData.size());
                        for (String personalDataItem : personalData) {
                            dos.writeUTF(personalDataItem);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = ((OrganizationSection) entry.getValue()).getOrganizations();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            writeOrganizationHomePage(dos, organization);

                            dos.writeInt(organization.getPosition().size());
                            for (Organization.Position position : organization.getPosition()) {
                                writeOrganizationPosition(dos, position);
                            }
                        }
                    }
                }
            }
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

            // TODO implements sections
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
                                positions.add(new Organization.Position(YearMonth.parse(dis.readUTF()),
                                        YearMonth.parse(dis.readUTF()), dis.readUTF(), dis.readUTF()));
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

    private void writeOrganizationHomePage(DataOutputStream dos, Organization organization) throws IOException {
        dos.writeUTF(organization.getHomePage().getName());
        dos.writeUTF(organization.getHomePage().getUrl());
    }

    private void writeOrganizationPosition(DataOutputStream dos, Organization.Position position) throws IOException {
        dos.writeUTF(position.getStartDate().toString());
        dos.writeUTF(position.getEndDate().toString());
        dos.writeUTF(position.getPosition());
        dos.writeUTF(position.getDescription());
    }
}
