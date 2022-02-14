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
    protected void updateResume(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        String uuid = (String) searchKey;
        return storageMap.get(uuid);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        String uuid = (String) searchKey;
        storageMap.remove(uuid);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
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
    protected Object getSearchKey(String uuid) {
        return storageMap.containsKey(uuid) ? uuid : "";
    }

    protected final boolean isExist (Object searchKey) {
        return !Objects.equals(searchKey, "");
    }
}
