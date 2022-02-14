package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumeList = new ArrayList<>();

    public final void clear() {
        resumeList.clear();
    }

    protected final void updateResume (Resume r, Object searchKey) {
        int index = (Integer) searchKey;
        resumeList.set(index, r);
    }

    protected final Resume getResume(Object searchKey) {
        int index = (Integer) searchKey;
        return resumeList.get(index);
    }

    protected final void deleteResume(Object searchKey) {
        int index = (Integer) searchKey;
        resumeList.remove(index);
    }

    protected final void saveResume(Resume r, Object searchKey) {
        resumeList.add(r);
    }

    public final Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    public final int size() {
        return resumeList.size();
    }

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected final boolean isExist (Object searchKey) {
        return (Integer) searchKey >= 0;
    }

}
