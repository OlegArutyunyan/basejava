package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.urise.webapp.ResumeTestData.createResume;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
    protected static final String NOT_EXIST_UUID = "uuid16";
    protected static final String NOT_EXIST_FULLNAME = "Herman Flow";
    protected static final List<Resume> EXPECTED_RESUME_LIST = new ArrayList<>();
    private static final String EXIST_UUID = "uuid2";
    private static final String EXIST_FULLNAME = "Mark Brown";
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeClass
    public static void setUpResumeModel() {
        EXPECTED_RESUME_LIST.clear();
        EXPECTED_RESUME_LIST.add(createResume("uuid1", "John Onion"));
        EXPECTED_RESUME_LIST.add(createResume("uuid2", "Mark Brown"));
        EXPECTED_RESUME_LIST.add(createResume("uuid3", "Tomas Dark"));
    }

    @Before
    public void setUp() {
        List<Resume> resumeList = new ArrayList<>();

        resumeList.add(createResume("uuid1", "John Onion"));
        resumeList.add(createResume("uuid3", "Tomas Dark"));
        resumeList.add(createResume("uuid2", "Mark Brown"));

        storage.clear();

        for (Resume resume : resumeList) {
            storage.save(resume);
        }
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = createResume(EXIST_UUID, EXIST_FULLNAME);

        storage.update(resume);
        assertSame(resume, storage.get(EXIST_UUID));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(createResume(NOT_EXIST_UUID, NOT_EXIST_FULLNAME));
    }

    @Test
    public void get() {
        assertEquals(createResume(EXIST_UUID, EXIST_FULLNAME), storage.get(EXIST_UUID));
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
        Resume resume = createResume(NOT_EXIST_UUID, NOT_EXIST_FULLNAME);
        storage.save(resume);
        assertEquals(4, storage.size());
        assertEquals(resume, storage.get(NOT_EXIST_UUID));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(createResume(EXIST_UUID, EXIST_FULLNAME));
    }

    @Test
    public void getAllSorted() {
        assertEquals(EXPECTED_RESUME_LIST, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}