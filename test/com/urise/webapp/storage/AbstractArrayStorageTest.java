package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public class AbstractArrayStorageTest {
    protected Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        for (int i = 1; i <= 3; i++) {
            storage.save(new Resume("uuid" + i));
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume("uuid2");

        storage.update(resume);
        Assert.assertEquals(storage.get(resume.getUuid()), resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuid16"));
    }

    @Test
    public void get() {
        Resume resume = new Resume("uuid2");

        Assert.assertEquals(resume, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("uuid16");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        Resume resume = new Resume("uuid2");

        storage.delete("uuid2");
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(resume, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid16");
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid6");

        try {
            for (int i = storage.size() + 1; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i));
            }
            Assert.assertEquals(10, storage.size());
            Assert.assertEquals(resume, storage.get("uuid6"));
        } catch (Exception e) {
            Assert.fail("Storage overflow occurred earlier than expected");
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume("uuid2"));
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        for (int i = storage.size(); i <= STORAGE_LIMIT + 1; i++) {
            storage.save(new Resume("uuid" + i));
        }
    }

    @Test
    public void getAll() {
        Assert.assertEquals(storage.size(), storage.getAll().length);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

}