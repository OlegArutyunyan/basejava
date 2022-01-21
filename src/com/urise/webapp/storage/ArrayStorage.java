package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public int size = 0;
    Resume[] storage = new Resume[10000];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        //Check if there is such resume in the storage
        boolean exist = false;

        for (int i = 0; i < size; i++) {
            if (r.getUuid() == storage[i].getUuid()) {
                exist = true;

                break;
            }
        }
        if (!exist) {
            System.out.println("No such resume!");
        }
    }

    public void save(Resume r) {
        //Check if there is such resume in the storage and if the storage is full
        boolean exist = false;

        if (size >= 10000) {
            System.out.println("Storage is full!");
        } else {
            for (int i = 0; i < size; i++) {
                if (r.getUuid() == storage[i].getUuid()) {
                    exist = true;
                    System.out.println("Resume already exists!");
                    break;
                }
            }
            if (!exist) {
                storage[size] = r;
                size++;
            }
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                return storage[i];
            }
        }
        System.out.println("No such resume!");
        return null;
    }

    public void delete(String uuid) {
        //Check if there is such resume in the storage
        boolean exist = false;

        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                for (int j = i; j < size - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                exist = true;
                storage[size - 1] = null;
                size--;
                break;
            }
        }
        if (!exist) {
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
