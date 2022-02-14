package com.urise.webapp.storage;


import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

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

    protected final void updateResume(Resume r, Object searchKey) {
        int index = (Integer) searchKey;
        storage[index] = r;
    }

    protected final Resume getResume(Object searchKey) {
        int index = (Integer) searchKey;
        return storage[index];
    }

    protected final void deleteResume(Object searchKey) {
        int index = (Integer) searchKey;
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        storage[size - 1] = null;
        size--;
    }

    protected final void saveResume(Resume r, Object searchKey) {
        int index = (Integer) searchKey;
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow!", r.getUuid());
        } else {
            saveResumeArray(r, index);
            size++;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected final boolean isExist (Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void saveResumeArray(Resume r, Object searchKey);
}
