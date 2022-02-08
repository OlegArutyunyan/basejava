package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest  extends AbstractStorageTest{
    private static final String NOT_EXIST_UUID = "uuid16";
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
        this.storage = storage;
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        for (int i = storage.size() + 1; i <= STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("uuid" + i));
            } catch (Exception e) {
                fail("Storage overflow occurred earlier than expected");
            }
        }
        storage.save(new Resume(NOT_EXIST_UUID));
    }
}