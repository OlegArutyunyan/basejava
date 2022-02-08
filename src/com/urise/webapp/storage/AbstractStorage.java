package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractStorage implements Storage{

    ArrayList<Resume> resumeList = new ArrayList<Resume>();

    public void clear() {
        resumeList.clear();
    }

    public void update(Resume r) {
        if (!resumeList.contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            for (int i = 0; i < resumeList.size(); i++) {
                if (r.equals(resumeList.get(i))) {
                    resumeList.set(i, r);
                }
            }
        }
    }

    public Resume get(String uuid) {
        for (Resume resume : resumeList) {
            if (uuid.equals(resume.getUuid())) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        for (Resume resume : resumeList) {
            if (uuid.equals(resume.getUuid())) {
                resumeList.remove(resume);
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    public void save(Resume r) {
        if (resumeList.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            resumeList.add(r);
        }
    }

    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    public int size() {
        return resumeList.size();
    }

}
