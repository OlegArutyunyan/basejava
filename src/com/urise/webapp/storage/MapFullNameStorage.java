package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFullNameStorage extends AbstractStorage {
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
        return (Resume) searchKey;
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storageMap.values().remove((Resume) searchKey);
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
        return storageMap.containsKey(uuid) ? storageMap.get(uuid) : "";
    }

    protected final boolean isExist(Object searchKey) {
        return searchKey != "";
    }
}