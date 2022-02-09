package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapStorageTest extends AbstractStorageTest {
    MapStorage mapStorage;

    public MapStorageTest() {
        super(new MapStorage());
        mapStorage = (MapStorage) storage;
    }

    @Test
    public void getAll() {
        List<Resume> expectedResumeList = Arrays.asList(EXPECTED_RESUME);
        List<Resume> actualResumeList = Arrays.asList(mapStorage.getAll());

        assertEquals(3, mapStorage.size());
        assertTrue(expectedResumeList.containsAll(actualResumeList) &&
                actualResumeList.containsAll(expectedResumeList));
    }
}