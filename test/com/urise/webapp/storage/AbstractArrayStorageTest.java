package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Test;

import static com.urise.webapp.ResumeTestData.createResume;
import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest  extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        for (int i = storage.size() + 1; i <= STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("uuid" + i, "User Name"));
            } catch (Exception e) {
                fail("Storage overflow occurred earlier than expected");
            }
        }
        storage.save(createResume(NOT_EXIST_UUID, NOT_EXIST_FULLNAME));
    }
}