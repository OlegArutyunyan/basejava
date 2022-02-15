package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storageMap.get((String) searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storageMap.remove((String) searchKey);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    public List<Resume> createResumeList() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    protected final boolean isExist (Object searchKey) {
        return storageMap.containsKey((String) searchKey);
    }
}
