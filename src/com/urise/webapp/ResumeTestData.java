package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    static Resume resume = new Resume("Григорий Кислин");

    public static void main(String[] args) {
        fillContacts(resume);
        fillSections(resume);
        System.out.println(resume.getFullName());
        printContacts(resume);
        System.out.println();
        printSections(resume);
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        fillContacts(resume);
        fillSections(resume);
        return resume;
    }

    private static void printContacts(Resume resume) {
        for (ContactType contact : ContactType.values()) {
            System.out.println(contact.getTitle() + ": " + resume.getContact(contact));
        }
    }

    private static void printSections(Resume resume) {
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
            resume.setContact(contact, String.valueOf(new SimpleLineSections(contactFields[i++])));
        }
    }

    private static void fillSections(Resume resume) {
        for (SectionType section : SectionType.values()) {
            switch (section) {
                case PERSONAL -> resume.setSection(SectionType.PERSONAL, new SimpleLineSections("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

                case OBJECTIVE -> resume.setSection(SectionType.OBJECTIVE, new SimpleLineSections("Аналитический склад ума, сильная логика, креативность, инициативность. " + "Пурист кода и архитектуры."));

                case ACHIEVEMENT -> {
                    List<String> objectiveList = new ArrayList<>();

                    objectiveList.add("С 2013 года: разработка проектов " + "\"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. " + "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " + "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " + "Более 1000 выпускников.");
                    objectiveList.add("Реализация двухфакторной аутентификации для онлайн платформы управления " + "проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

                    resume.setSection(SectionType.ACHIEVEMENT, new ListSections(objectiveList));
                }

                case QUALIFICATIONS -> {
                    List<String> qualificationsList = new ArrayList<>();

                    qualificationsList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
                    qualificationsList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
                    qualificationsList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
                    qualificationsList.add("MySQL, SQLite, MS SQL, HSQLDB");

                    resume.setSection(SectionType.QUALIFICATIONS, new ListSections(qualificationsList));
                }

                case EXPERIENCE -> {
                    List<Experience> experienceList = new ArrayList<>();

                    experienceList.add(new Experience("Java Online Projects", "http://javaops.ru/", YearMonth.of(2013, 10), YearMonth.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
                    experienceList.add(new Experience("Wrike", "https://www.wrike.com/", YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Старший разработчик (backend).", "Проектирование и разработка онлайн платформы управления проектами Wrike " + "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " + "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
                    experienceList.add(new Experience("RIT Center", "", YearMonth.of(2012, 4), YearMonth.of(2014, 10), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));

                    resume.setSection(SectionType.EXPERIENCE, new Organization(experienceList));
                }

                case EDUCATION -> {
                    List<Experience> educationList = new ArrayList<>();

                    educationList.add(new Experience("Coursera", "https://www.coursera.org/course/progfun", YearMonth.of(2013, 3), YearMonth.of(2013, 5), "", "\"Functional Programming Principles in Scala\" by Martin Odersky"));
                    educationList.add(new Experience("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", YearMonth.of(2011, 3), YearMonth.of(2011, 4), "", "Курс \"Объектно-ориентированный анализ ИС. " + "Концептуальное моделирование на UML.\""));

                    resume.setSection(SectionType.EDUCATION, new Organization(educationList));
                }
            }
        }
    }
}
