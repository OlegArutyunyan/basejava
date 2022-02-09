package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected void updateResume(Resume r, int index) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(String uuid, int index) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        resumeMap.remove(uuid);
    }

    @Override
    protected void saveResume(Resume r, int index) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    protected int getIndex(Resume resume) {
        if (resumeMap.containsValue(resume)) {
            return 1;
        }
        return -1;
    }
}
