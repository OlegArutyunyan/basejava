package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
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

    protected final void updateResume(Resume r, int index) {
        storage[index] = r;
    }

    protected final Resume getResume(int index) {
        return storage[index];
    }

    protected final void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        storage[size - 1] = null;
        size--;
    }

    protected final void saveResume(Resume r, int index) {
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

    protected abstract int getIndex(Resume resume);

    protected abstract void saveResumeArray(Resume r, int index);
}
