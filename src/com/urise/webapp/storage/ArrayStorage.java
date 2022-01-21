package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public int size = 0;
    public int count = 0;
    Resume[] storage = new Resume[10000];

    /* Attempt to create additional method to avoid use of duplicate code
     * Method is used to check if the resume is existing. */
    boolean resumeExistence(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                count = i;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        boolean exist = resumeExistence(r.getUuid());

        if (exist) {
            storage[count].setUuid(r.getUuid());
        } else {
            System.out.println("No such resume!");
        }
    }

    public void save(Resume r) {
        boolean exist = resumeExistence(r.getUuid());

        if (size >= 10000) {
            System.out.println("Storage is full!");
        } else {
            if (!exist) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Resume already exists!");
            }
        }
    }

    public Resume get(String uuid) {
        boolean exist = resumeExistence(uuid);

        if (exist) {
            return storage[count];
        } else {
            System.out.println("No such resume!");
            return null;
        }
    }

    public void delete(String uuid) {
        boolean exist = resumeExistence(uuid);

        if (exist) {
            for (int j = count; j < size - 1; j++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("No such resume. Nothing to delete!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
