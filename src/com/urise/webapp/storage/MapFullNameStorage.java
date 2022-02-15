package com.urise.webapp.storage;

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
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(storageMap.values());
        resumes.sort(RESUME_COMPARATOR);
        return resumes;
    }

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storageMap.get(uuid).getFullName();
    }

    protected final boolean isExist (Object searchKey) {
        for (Map.Entry<String, Resume> resume : storageMap.entrySet()) {
            if (resume.getValue().getFullName().equals(searchKey)) {
                return true;
            }
        }
        return false;
    }
}
