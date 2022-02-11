package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumeList = new ArrayList<>();

    public final void clear() {
        resumeList.clear();
    }

    protected final void updateResume (Resume r, int index) {
        resumeList.set(index, r);
    }

    protected final Resume getResume(String uuid, int index) {
        return resumeList.get(index);
    }

    protected final void deleteResume(String uuid, int index) {
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
