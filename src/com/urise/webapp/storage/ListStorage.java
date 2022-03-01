package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> resumeList = new ArrayList<>();

    public void clear() {
        resumeList.clear();
    }

    protected void updateResume(Resume r, Integer index) {
        resumeList.set(index, r);
    }

    protected Resume getResume(Integer index) {
        return resumeList.get(index);
    }

    protected void deleteResume(Integer index) {
        resumeList.remove((int) index);
    }

    protected void saveResume(Resume r, Integer searchKey) {
        resumeList.add(r);
    }

    public List<Resume> createResumeList() {
        return resumeList;
    }

    public int size() {
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

    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

}
