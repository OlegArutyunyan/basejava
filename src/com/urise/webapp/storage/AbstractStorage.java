package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractStorage implements Storage{
    public final void update(Resume r) {
        int index = getIndex(r);

        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r, index);
        }
    }

    public final Resume get(String uuid) {
        int index = getIndex(new Resume(uuid));

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    public final void delete(String uuid) {
        int index = getIndex(new Resume(uuid));

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    public final void save(Resume r) {
        int index = getIndex(r);

        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(r, index);
        }
    }

    protected abstract int getIndex(Resume resume);

    protected abstract void updateResume(Resume r, int index);

    protected abstract Resume getResume(int index);

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume r, int index);
}
