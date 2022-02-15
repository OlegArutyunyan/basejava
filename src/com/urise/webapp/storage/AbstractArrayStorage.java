package com.urise.webapp.storage;


import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected final void updateResume(Resume r, Object index) {
        storage[(int) index] = r;
    }

    protected final Resume getResume(Object index) {
        return storage[(int) index];
    }

    protected final void deleteResume(Object index) {
        System.arraycopy(storage, (int) index + 1, storage, (int) index, size - 1 - (int) index);
        storage[size - 1] = null;
        size--;
    }

    protected final void saveResume(Resume r, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow!", r.getUuid());
        } else {
            saveResumeArray(r, (int) index);
            size++;
        }
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumes = Arrays.asList(storage);
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }

    public int size() {
        return size;
    }

    protected final boolean isExist (Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void saveResumeArray(Resume r, Integer searchKey);
}















