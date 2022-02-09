package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void saveResumeArray(Resume r, int index) {
        storage[size] = r;
    }

    protected int getIndex(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (resume.equals(storage[i])) {
                return i;
            }
        }
        return -1;
    }
}
