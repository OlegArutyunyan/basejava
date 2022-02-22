package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> resumeList = new ArrayList<>();

    public final void clear() {
        resumeList.clear();
    }

    protected final void updateResume (Resume r, Integer index) {
        resumeList.set(index, r);
    }

    protected final Resume getResume(Integer index) {
        return resumeList.get(index);
    }

    protected final void deleteResume(Integer index) {
        resumeList.remove((int) index);
    }

    protected final void saveResume(Resume r, Integer searchKey) {
        resumeList.add(r);
    }

    public final List<Resume> createResumeList() {
        return resumeList;
    }

    public final int size() {
        return resumeList.size();
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected final boolean isExist (Integer searchKey) {
        return searchKey >= 0;
    }

}
