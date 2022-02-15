package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

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
        return storageMap.get(mapKey(searchKey));
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storageMap.remove(mapKey(searchKey));
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
        return storageMap.containsKey(uuid) ? storageMap.get(uuid).getFullName() : "";
    }

    protected final boolean isExist (Object searchKey) {
        return mapKey(searchKey) != null;
    }

    private String mapKey (Object searchKey) {
        for (Map.Entry<String, Resume> resume : storageMap.entrySet()) {
            if (resume.getValue().getFullName().equals(searchKey)) {
                return resume.getKey();
            }
        }
        return null;
    }
}
