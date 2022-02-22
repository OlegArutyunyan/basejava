package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected void updateResume(Resume r, Resume searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void deleteResume(Resume searchKey) {
        storageMap.remove(searchKey.getUuid());
    }

    @Override
    protected void saveResume(Resume r, Resume searchKey) {
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
    protected Resume getSearchKey(String uuid) {
        return storageMap.get(uuid);
    }

    protected final boolean isExist(Resume searchKey) {
        return searchKey != null;
    }
}