package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateResume(Resume r, String searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return storageMap.get(searchKey);
    }

    @Override
    protected void deleteResume(String searchKey) {
        storageMap.remove(searchKey);
    }

    @Override
    protected void saveResume(Resume r, String searchKey) {
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
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    protected final boolean isExist (String searchKey) {
        return storageMap.containsKey(searchKey);
    }
}
