package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage{
    ArrayList<Resume> resumeList = new ArrayList<Resume>();

    public final void clear() {
        resumeList.clear();
    }

    public final void update(Resume r) {
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

    public final Resume get(String uuid) {
        for (Resume resume : resumeList) {
            if (uuid.equals(resume.getUuid())) {
                return resume;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    public final void delete(String uuid) {
        for (Resume resume : resumeList) {
            if (uuid.equals(resume.getUuid())) {
                resumeList.remove(resume);
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    public final void save(Resume r) {
        if (resumeList.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            resumeList.add(r);
        }
    }

    public final Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    public final int size() {
        return resumeList.size();
    }
}
