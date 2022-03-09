package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = createResume("uuid1", "Григорий Кислин");
        System.out.println(resume.getFullName());
        printContacts(resume);
        printSections(resume);
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        fillContacts(resume);
        fillSections(resume);
        return resume;
    }

    private static void printContacts(Resume resume) {
        System.out.println();
        for (ContactType contact : ContactType.values()) {
            System.out.println(contact.getTitle() + ": " + resume.getContact(contact));
        }
    }

    private static void printSections(Resume resume) {
        System.out.println();
        for (SectionType section : SectionType.values()) {
            System.out.println(section.getTitle());
            System.out.println(resume.getSection(section));
            System.out.println();
        }
    }

    private static void fillContacts(Resume resume) {
        String[] contactFields = {"+7(921) 855-0482", "grigory.kislin", "gkislin@yandex.ru", "Профиль LinkedIn", "Профиль GitHub", "Профиль Stackoverflow", "Домашняя страница"};
        int i = 0;

        for (ContactType contact : ContactType.values()) {
            resume.setContact(contact, String.valueOf(new TextSection(contactFields[i++])));
        }
    }

    private static void fillSections(Resume resume) {
        for (SectionType section : SectionType.values()) {
            switch (section) {
                case PERSONAL -> resume.setSection(SectionType.PERSONAL, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

                case OBJECTIVE -> resume.setSection(SectionType.OBJECTIVE, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. " + "Пурист кода и архитектуры."));

                case ACHIEVEMENT -> {
                    List<String> objectiveList = new ArrayList<>();

                    objectiveList.add("С 2013 года: разработка проектов " + "\"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " + "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " + "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " + "Более 1000 выпускников.");
                    objectiveList.add("Реализация двухфакторной аутентификации для онлайн платформы управления " + "проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

                    resume.setSection(SectionType.ACHIEVEMENT, new ListSection(objectiveList));
                }

                case QUALIFICATIONS -> {
                    List<String> qualificationsList = new ArrayList<>();

                    qualificationsList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
                    qualificationsList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
                    qualificationsList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
                    qualificationsList.add("MySQL, SQLite, MS SQL, HSQLDB");

                    resume.setSection(SectionType.QUALIFICATIONS, new ListSection(qualificationsList));
                }

                case EXPERIENCE -> {
                    List<Organization> organizationList = new ArrayList<>();
                    List<Organization.Position> positionDatesJavaops = new ArrayList<>();
                    List<Organization.Position> positionDatesWrike = new ArrayList<>();
                    List<Organization.Position> positionDatesRit = new ArrayList<>();

                    positionDatesJavaops.add(new Organization.Position(YearMonth.of(2013, 10), YearMonth.now()
                            , "Автор проекта."
                            , "Создание, организация и проведение Java онлайн проектов и стажировок."));
                    organizationList.add(
                            new Organization("Java Online Projects", "http://javaops.ru/"
                                    , positionDatesJavaops));


                    positionDatesWrike.add(new Organization.Position(YearMonth.of(2014, 10)
                            , YearMonth.of(2016, 1)
                            , "Старший разработчик (backend)."
                            , "Проектирование и разработка онлайн платформы управления проектами Wrike "
                            + "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). "
                            + "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
                    organizationList.add(new Organization("Wrike", "https://www.wrike.com/"
                            , positionDatesWrike));

                    positionDatesRit.add(new Organization.Position(YearMonth.of(2012, 4)
                            , YearMonth.of(2014, 10)
                            , "Автор проекта."
                            , "Создание, организация и проведение Java онлайн проектов и стажировок."));
                    organizationList.add(new Organization("RIT Center", "", positionDatesRit));

                    resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(organizationList));
                }

                case EDUCATION -> {
                    List<Organization> educationList = new ArrayList<>();
                    List<Organization.Position> educationDatesCoursera = new ArrayList<>();
                    List<Organization.Position> educationDatesIfmo = new ArrayList<>();

                    educationDatesCoursera.add(new Organization.Position(YearMonth.of(2013, 3)
                            , YearMonth.of(2013, 5)
                            , "", "\"Functional Programming Principles in Scala\" by Martin Odersky"));
                    educationList.add(new Organization("Coursera", "https://www.coursera.org/course/progfun"
                            , educationDatesCoursera));

                    educationDatesIfmo.add(new Organization.Position(YearMonth.of(1993, 9)
                            , YearMonth.of(1996, 7), ""
                            , "Аспирантура (программист С, С++)"));
                    educationDatesIfmo.add(new Organization.Position(YearMonth.of(1987, 9)
                            , YearMonth.of(1993, 7), ""
                            , "Инженер (программист Fortran, C)"));
                    educationList.add(new Organization("Санкт-Петербургский национальный исследовательский " +
                            "университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"
                            , educationDatesIfmo));

                    resume.setSection(SectionType.EDUCATION, new OrganizationSection(educationList));
                }
            }
        }
    }
}
