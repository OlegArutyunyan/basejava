package com.urise.webapp.storage;


import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected final void updateResume(Resume r, Integer index) {
        storage[index] = r;
    }

    protected final Resume getResume(Integer index) {
        return storage[index];
    }

    protected final void deleteResume(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        storage[size - 1] = null;
        size--;
    }

    protected final void saveResume(Resume r, Integer index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow!", r.getUuid());
        }
        saveResumeArray(r, index);
        size++;
    }

    public List<Resume> createResumeList() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public int size() {
        return size;
    }

    protected final boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void saveResumeArray(Resume r, Integer searchKey);
}