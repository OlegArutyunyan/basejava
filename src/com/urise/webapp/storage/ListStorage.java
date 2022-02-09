package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> resumeList = new ArrayList<Resume>();

    public final void clear() {
        resumeList.clear();
    }

    protected final void updateResume (Resume r, int index) {
        resumeList.set(index, r);
    }

    protected final Resume getResume(int index) {
        return resumeList.get(index);
    }

    protected final void deleteResume(int index) {
        resumeList.remove(index);
    }

    protected final void saveResume(Resume r, int index) {
        resumeList.add(r);
    }

    public final Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    public final int size() {
        return resumeList.size();
    }

    protected int getIndex(Resume resume) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resume.equals(resumeList.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
