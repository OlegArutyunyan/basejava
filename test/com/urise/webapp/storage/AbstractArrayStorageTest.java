package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private static final String EXIST_UUID = "uuid2";
    private static final String NOT_EXIST_UUID = "uuid16";
    private static final Resume[] EXPECTED_RESUME = new Resume[3];
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeClass
    public static void setUpResumeModel() {
        EXPECTED_RESUME[0] = new Resume("uuid1");
        EXPECTED_RESUME[1] = new Resume("uuid2");
        EXPECTED_RESUME[2] = new Resume("uuid3");
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        for (int i = 1; i <= 3; i++) {
            storage.save(new Resume("uuid" + i));
        }
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(EXIST_UUID);

        storage.update(resume);
        assertSame(resume, storage.get(EXIST_UUID));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(NOT_EXIST_UUID));
    }

    @Test
    public void get() {
        assertEquals(new Resume(EXIST_UUID), storage.get(EXIST_UUID));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(NOT_EXIST_UUID);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(EXIST_UUID);
        assertEquals(2, storage.size());
        storage.get(EXIST_UUID);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(NOT_EXIST_UUID);
    }

    @Test
    public void save() {
        storage.save(new Resume(NOT_EXIST_UUID));
        assertEquals(4, storage.size());
        storage.get(NOT_EXIST_UUID);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(EXIST_UUID));
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

    @Test
    public void getAll() {
        assertArrayEquals(EXPECTED_RESUME, storage.getAll());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

}