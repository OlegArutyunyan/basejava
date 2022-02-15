package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MapFullNameStorageTest extends AbstractStorageTest {

    public MapFullNameStorageTest() {
        super(new MapFullNameStorage());
    }

    @Test
    public void getAllSorted() {
        Resume [] resumeArray =  storage.getAllSorted().toArray(new Resume[0]);

        assertEquals(3, storage.size());
        assertArrayEquals(EXPECTED_RESUME, resumeArray);
    }
}