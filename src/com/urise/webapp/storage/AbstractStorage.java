package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage{
    public final void update(Resume r) {
        updateResume(r, checkIfExist(r, ""));
    }

    public final Resume get(String uuid) {
        return getResume(uuid, checkIfExist(new Resume(uuid), ""));
    }

    public final void delete(String uuid) {
        deleteResume(uuid, checkIfExist(new Resume(uuid), ""));
    }

    public final void save(Resume r) {
        saveResume(r, checkIfExist(r, "save"));
    }

    private int checkIfExist(Resume r, String method){
        int index = getIndex(r);
        boolean saveMethodUsed = Objects.equals(method, "save");

        if (saveMethodUsed && index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (!saveMethodUsed && index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }

        return index;
    }

    protected abstract int getIndex(Resume resume);

    protected abstract void updateResume(Resume r, int index);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract void deleteResume(String uuid, int index);

    protected abstract void saveResume(Resume r, int index);
}
