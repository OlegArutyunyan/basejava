package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void saveResumeArray(Resume r, int binaryIndex) {
        int index = - binaryIndex - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected int getIndex(Resume resume) {
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}
