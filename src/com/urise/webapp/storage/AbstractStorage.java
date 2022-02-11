package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Objects;

public abstract class AbstractStorage implements Storage{
    public final void update(Resume r) {
        updateResume(r, checkIfExist(r.getUuid(), ""));
    }

    public final Resume get(String uuid) {
        return getResume(uuid, checkIfExist(uuid, ""));
    }

    public final void delete(String uuid) {
        deleteResume(uuid, checkIfExist(uuid, ""));
    }

    public final void save(Resume r) {
        saveResume(r, checkIfExist(r.getUuid(), "save"));
    }

    private int checkIfExist(String uuid, String method){
        int index = getIndex(uuid);
        boolean saveMethodUsed = Objects.equals(method, "save");

        if (saveMethodUsed && index >= 0) {
            throw new ExistStorageException(uuid);
        } else if (!saveMethodUsed && index < 0) {
            throw new NotExistStorageException(uuid);
        }

        return index;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume r, int index);

    protected abstract Resume getResume(String uuid, int index);

    protected abstract void deleteResume(String uuid, int index);

    protected abstract void saveResume(Resume r, int index);
}
