package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage{

    protected static final Comparator<Resume> RESUME_COMPARATOR
            = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract boolean isExist (Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Resume r, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract void saveResume(Resume r, Object searchKey);

    public final void update(Resume r) {
        updateResume(r, checkIfNotExist(r.getUuid()));
    }

    public final Resume get(String uuid) {
        return getResume(checkIfNotExist(uuid));
    }

    public final void delete(String uuid) {
        deleteResume(checkIfNotExist(uuid));
    }

    public final void save(Resume r) {
        saveResume(r, checkIfExist(r.getUuid()));
    }

    private Object checkIfNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object checkIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);

        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
