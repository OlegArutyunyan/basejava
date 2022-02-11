package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateResume(Resume r, int index) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(String uuid, int index) {
        return storageMap.get(uuid);
    }

    @Override
    protected void deleteResume(String uuid, int index) {
        storageMap.remove(uuid);
    }

    @Override
    protected void saveResume(Resume r, int index) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    public Resume[] getAll() {
        return storageMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    protected int getIndex(Resume resume) {
        return storageMap.containsValue(resume) ? 1 : -1;
    }
}
