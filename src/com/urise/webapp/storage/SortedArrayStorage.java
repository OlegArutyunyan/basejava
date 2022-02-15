package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void saveResumeArray(Resume r, Integer binaryIndex) {
        int index = - binaryIndex - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size
                , new Resume(uuid, "John Doe"), SORTED_RESUME_COMPARATOR);
    }

    private static final Comparator<Resume> SORTED_RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);
}
