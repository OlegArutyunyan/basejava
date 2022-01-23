package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    /* Attempt to create additional method to avoid use of duplicate code
     * Method is used to check if the resume is existing. */
    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(String oldUuid, String newUuid) {
        int index = findIndex(oldUuid);

        if (index >= 0) {
            storage[index].setUuid(newUuid);
        } else {
            System.out.println("No resume " + oldUuid + " found!");
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());

        if (size >= storage.length) {
            System.out.println("Storage is full!");
        } else if (index == -1) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume " + r.getUuid() + " already exists!");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);

        if (index >= 0) {
            return storage[index];
        }
        System.out.println("No resume " + uuid + " found!");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);

        if (index >= 0) {
            if (size - 1 - index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("No resume " + uuid + " found. Nothing to delete!");
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
